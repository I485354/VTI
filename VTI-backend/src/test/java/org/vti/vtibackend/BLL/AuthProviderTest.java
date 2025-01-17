package org.vti.vtibackend.BLL;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.security.Key;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JwtTokenProviderTest {

    private final Key key = Keys. secretKeyFor(SignatureAlgorithm.HS256);

    @Mock
    private HttpServletRequest httpServletRequest;

    @InjectMocks
    private JwtTokenProvider jwtTokenProvider;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        jwtTokenProvider = new JwtTokenProvider();
    }

    @Test
    void testGenerateTokenAndValidate() {
        // Arrange
        String username = "testuser";
        String role = "ADMIN";

        // Act
        String token = jwtTokenProvider.generateToken(username, role);

        // Assert
        assertNotNull(token);
        assertTrue(jwtTokenProvider.validateToken(token), "Token should be valid");

        // Check claims
        assertEquals(username, jwtTokenProvider.getUsername(token));
        assertEquals(role, jwtTokenProvider.getRole(token));
    }

    @Test
    void testInvalidToken() {
        // Arrange
        String invalidToken = "invalidToken";

        // Act & Assert
        assertFalse(jwtTokenProvider.validateToken(invalidToken), "Token should be invalid");
    }

    @Test
    void testExpiredToken() {
        // Arrange
        JwtTokenProvider providerWithShortExpiry = new JwtTokenProvider() {
            @Override
            public String generateToken(String username, String role) {
                long validityInMilliseconds = 1;
                return Jwts.builder()
                        .setSubject(username)
                        .claim("role", role)
                        .setIssuedAt(new Date())
                        .setExpiration(new Date(System.currentTimeMillis() + validityInMilliseconds))
                        .signWith(key)
                        .compact();
            }
        };

        String expiredToken = providerWithShortExpiry.generateToken("testuser", "ADMIN");


        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }


        assertFalse(providerWithShortExpiry.validateToken(expiredToken), "Expired token should be invalid");
    }

    @Test
    void testResolveToken() {
        // Arrange
        String token = "Bearer testToken";
        when(httpServletRequest.getHeader("Authorization")).thenReturn(token);

        // Act
        String resolvedToken = jwtTokenProvider.resolveToken(httpServletRequest);

        // Assert
        assertEquals(token, resolvedToken);
        verify(httpServletRequest).getHeader("Authorization");
    }

    @Test
    void testResolveToken_NoAuthorizationHeader() {
        // Arrange
        when(httpServletRequest.getHeader("Authorization")).thenReturn(null);

        // Act
        String resolvedToken = jwtTokenProvider.resolveToken(httpServletRequest);

        // Assert
        assertNull(resolvedToken, "Should return null if no Authorization header is present");
    }

    @Test
    void testValidateTokenWithSignatureException() {
        // Arrange
        String fakeToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJmYWtlVXNlciIsInJvbGUiOiJBRE1JTiJ9.invalidSignature";

        // Act & Assert
        assertFalse(jwtTokenProvider.validateToken(fakeToken), "Token with invalid signature should be invalid");
    }
}