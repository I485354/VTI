package org.vti.vtibackend.BLL;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException
    {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            // Check of token ongeldig/verlopen is
            if (!jwtTokenProvider.validateToken(token)) {
                // Token is niet geldig: stuur 401 en stop
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            // Eventueel: Claims uit de token halen en in SecurityContext zetten
        }

        // Ga verder in de chain
        filterChain.doFilter(request, response);
    }
}
