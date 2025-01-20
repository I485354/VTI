package org.vti.vtibackend.Presentatie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.vti.vtibackend.BLL.JwtTokenProvider;
import org.vti.vtibackend.BLL.Service.UserService;
import org.vti.vtibackend.Presentatie.Controllers.UserController;
import org.vti.vtibackend.model.User.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @InjectMocks
    private UserController userController;

    private CreateUserDTO createUserDTO;
    private UserDTO userDTO;
    private UserInfo userInfo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        createUserDTO = new CreateUserDTO("john_doe", "password123");
        userDTO = new UserDTO(1L, "john_doe", "ADMIN");
        userInfo = new UserInfo(1, "john_doe", "ADMIN");
    }

    @Test
    void testGetAllUsers() {
        // Arrange
        List<UserInfo> users = new ArrayList<>();
        users.add(userInfo);
        when(userService.getAllUsers()).thenReturn(users);

        // Act
        List<UserInfo> result = userController.getAllUsers();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(userService).getAllUsers();
    }

    @Test
    void testCreateUser() {
        // Arrange
        when(userService.createUser(createUserDTO)).thenReturn(userDTO);

        // Act
        ResponseEntity<UserDTO> response = userController.createUser(createUserDTO);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("john_doe", response.getBody().getUsername());
        verify(userService).createUser(createUserDTO);
    }

    @Test
    void testGetUserByUsername() {
        // Arrange
        String username = "john_doe";
        when(userService.findByUsername(username)).thenReturn(userDTO);

        // Act
        ResponseEntity<UserDTO> response = userController.getUserByUsername(username);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("john_doe", response.getBody().getUsername());
        verify(userService).findByUsername(username);
    }

    @Test
    void testAuthenticate_Success() {
        // Arrange
        String token = "validToken";
        when(userService.authenticate(createUserDTO.getUsername(), createUserDTO.getPassword())).thenReturn(userDTO);
        when(jwtTokenProvider.generateToken(userDTO.getUsername(), userDTO.getRole())).thenReturn(token);

        // Act
        ResponseEntity<?> response = userController.authenticate(createUserDTO);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(userService).authenticate(createUserDTO.getUsername(), createUserDTO.getPassword());
        verify(jwtTokenProvider).generateToken(userDTO.getUsername(), userDTO.getRole());
    }

    @Test
    void testAuthenticate_Failure() {
        // Arrange
        when(userService.authenticate(createUserDTO.getUsername(), createUserDTO.getPassword()))
                .thenThrow(new org.springframework.security.authentication.BadCredentialsException("Invalid credentials"));

        // Act
        ResponseEntity<?> response = userController.authenticate(createUserDTO);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Ongeldige inloggegevens", response.getBody());
        verify(userService).authenticate(createUserDTO.getUsername(), createUserDTO.getPassword());
    }

    @Test
    void testGetUserInfo() {
        // Arrange
        List<UserInfo> users = new ArrayList<>();
        users.add(userInfo);
        when(userService.getUserInfo()).thenReturn(users);

        // Act
        ResponseEntity<List<UserInfo>> response = userController.getUserInfo();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(userService).getUserInfo();
    }

    @Test
    void testUpdateUser_Success() {
        // Arrange
        UpdatedUser updatedUser = new UpdatedUser("new_username", "USER");
        UserInfo updatedUserInfo = new UserInfo(1, "new_username", "USER");

        when(userService.updateUser(eq(1), any(UserInfo.class))).thenReturn(updatedUserInfo);

        // Act
        ResponseEntity<UserInfo> response = userController.updateUser(1, updatedUser);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("new_username", response.getBody().getUsername());
        verify(userService).updateUser(eq(1), any(UserInfo.class));
    }

    @Test
    void testUpdateUser_NotFound() {
        // Arrange
        UpdatedUser updatedUser = new UpdatedUser("new_username", "USER");
        when(userService.updateUser(eq(999), any(UserInfo.class)))
                .thenThrow(new jakarta.persistence.EntityNotFoundException("User not found"));

        // Act
        ResponseEntity<UserInfo> response = userController.updateUser(999, updatedUser);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(userService).updateUser(eq(999), any(UserInfo.class));
    }
}
