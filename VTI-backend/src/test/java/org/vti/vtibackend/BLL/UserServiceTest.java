package org.vti.vtibackend.BLL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.vti.vtibackend.BLL.Interface.IUserDAL;
import org.vti.vtibackend.BLL.Service.UserService;
import org.vti.vtibackend.model.User.CreateUserDTO;
import org.vti.vtibackend.model.User.UserDTO;
import org.vti.vtibackend.model.User.UserInfo;


import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;
public class UserServiceTest {

    @Mock
    private IUserDAL userDAL;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;



    private UserDTO user1;
    private UserInfo userInfo;
    private CreateUserDTO createUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user1 = new UserDTO(1, "john_doe", "password123", "admin");
        userInfo = new UserInfo(1, "john_doe", "admin");
        createUser = new CreateUserDTO("jane_doe", "securePass", 1);
    }

    @Test
    void getAllUsers() {

        // Arrange
        when(userDAL.findAll()).thenReturn(Arrays.asList(userInfo));

        // Act
        List<UserInfo> users = userService.getAllUsers();

        // Assert
        assertThat(users).hasSize(1);
        assertThat(users.get(0)).isEqualTo(userInfo);


        verify(userDAL, times(1)).findAll();
    }
    @Test
    void getAllUsers_EmptyList() {
        // Arrange
        when(userDAL.findAll()).thenReturn(Arrays.asList());

        // Act
        List<UserInfo> users = userService.getAllUsers();

        // Assert
        assertThat(users).isEmpty();
        verify(userDAL, times(1)).findAll();
    }


    @Test
    void createUser_Success() {

        when(userDAL.findByUsername(createUser.getUsername())).thenReturn(null);

        when(passwordEncoder.encode("securePass")).thenReturn("encodedPassword");

        UserDTO savedUser = new UserDTO();
        savedUser.setUser_id(2L);
        savedUser.setUsername("jane_doe");
        savedUser.setPassword("encodedPassword");
        savedUser.setRole("admin");

        when(userDAL.save(any(UserDTO.class))).thenReturn(savedUser);

        // Act
        UserDTO createdUser = userService.createUser(createUser);

        // Assert
        assertThat(createdUser).isNotNull();
        assertThat(createdUser.getUser_id()).isEqualTo(2L);
        assertThat(createdUser.getUsername()).isEqualTo("jane_doe");
        assertThat(createdUser.getPassword()).isEqualTo("encodedPassword");
        assertThat(createdUser.getRole()).isEqualTo("admin");

        verify(userDAL).findByUsername("jane_doe");
        verify(userDAL).save(any(UserDTO.class));
        verify(passwordEncoder).encode("securePass");
    }

    @Test
    void createUser_UserAlreadyExists() {
        UserDTO existing = new UserDTO(10L, "jane_doe", "someEncodedPass", "admin");
        when(userDAL.findByUsername("jane_doe")).thenReturn(existing);

        assertThatThrownBy(() -> userService.createUser(createUser))
                .isInstanceOf(BadCredentialsException.class)
                .hasMessage("Username already exists");


        verify(userDAL, never()).save(any());
    }

    @Test
    void createUser_NullInput() {

        assertThatThrownBy(() -> userService.createUser(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("User cannot be null");


        verify(userDAL, never()).save(any());
    }
    @Test
    void findByUsername_Found() {
        when(userDAL.findByUsername("john_doe")).thenReturn(user1);

        UserDTO result = userService.findByUsername("john_doe");

        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("john_doe");
        verify(userDAL).findByUsername("john_doe");
    }

    @Test
    void findByUsername_NotFound() {
        when(userDAL.findByUsername("unknown_user")).thenReturn(null);

        UserDTO result = userService.findByUsername("unknown_user");

        assertThat(result).isNull();
        verify(userDAL).findByUsername("unknown_user");
    }


    @Test
    void authenticate_Success() {

        // Arrange
        String rawPassword = "plainTextPass";
        String encodedPass = "encodedPass123";


        UserDTO dbUser = new UserDTO(1L, "john_doe", encodedPass, "admin");

        when(userDAL.authenticateUser("john_doe", encodedPass)).thenReturn(dbUser);


        when(passwordEncoder.encode(rawPassword)).thenReturn(encodedPass);
        when(passwordEncoder.matches(rawPassword, encodedPass)).thenReturn(true);

        // Act
        UserDTO authenticatedUser = userService.authenticate("john_doe", rawPassword);

        // Assert
        assertThat(authenticatedUser.getUser_id()).isEqualTo(1L);
        assertThat(authenticatedUser.getUsername()).isEqualTo("john_doe");
        assertThat(authenticatedUser.getPassword()).isNull();


        verify(userDAL).authenticateUser("john_doe", encodedPass);
        verify(passwordEncoder).matches(rawPassword, encodedPass);
    }

    @Test
    void authenticate_WrongPassword() {
        String rawPassword = "wrongPass";
        String encodedPass = "encodedPass123";

        UserDTO dbUser = new UserDTO(1L, "john_doe", encodedPass, "admin");

        when(passwordEncoder.encode(rawPassword)).thenReturn(encodedPass);
        when(userDAL.authenticateUser("john_doe", encodedPass)).thenReturn(dbUser);
        when(passwordEncoder.matches(rawPassword, encodedPass)).thenReturn(false);


        assertThatThrownBy(() -> userService.authenticate("john_doe", rawPassword))
                .isInstanceOf(BadCredentialsException.class)
                .hasMessage("Ongeldige inloggegevens");

        verify(userDAL).authenticateUser("john_doe", encodedPass);
        verify(passwordEncoder).matches(rawPassword, encodedPass);
    }


    @Test
    void getUserInfo_Success() {
        UserDTO u1 = new UserDTO(1L, "alice", "pass", "ADMIN");
        UserDTO u2 = new UserDTO(2L, "bob", "pass2", "USER");

        when(userDAL.getUsernamesAndRoles()).thenReturn(List.of(u1, u2));

        List<UserInfo> result = userService.getUserInfo();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getUsername()).isEqualTo("alice");
        assertThat(result.get(0).getRole()).isEqualTo("ADMIN");
        assertThat(result.get(1).getUsername()).isEqualTo("bob");
        assertThat(result.get(1).getRole()).isEqualTo("USER");

        verify(userDAL).getUsernamesAndRoles();
    }

    @Test
    void getUserInfo_Empty() {
        when(userDAL.getUsernamesAndRoles()).thenReturn(List.of());

        List<UserInfo> result = userService.getUserInfo();

        assertThat(result).isEmpty();
        verify(userDAL).getUsernamesAndRoles();
    }


    @Test
    void updateUser_Success() {
        UserInfo inputUser = new UserInfo(2, "new_username", "USER");
        UserDTO updatedDTO = new UserDTO(2L, "new_username", null, "USER");


        when(userDAL.UpdateUser(2, inputUser)).thenReturn(updatedDTO);

        UserInfo result = userService.updateUser(2, inputUser);

        assertThat(result.getUser_id()).isEqualTo(2);
        assertThat(result.getUsername()).isEqualTo("new_username");
        assertThat(result.getRole()).isEqualTo("USER");

        verify(userDAL).UpdateUser(2, inputUser);
    }

    @Test
    void updateUser_NotFoundScenario() {
        doThrow(new RuntimeException("Gebruiker niet gevonden"))
                .when(userDAL).UpdateUser(eq(2), any());

        UserInfo inputUser = new UserInfo(2, "new_username", "USER");

        assertThatThrownBy(() -> userService.updateUser(2, inputUser))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Gebruiker niet gevonden");

        verify(userDAL).UpdateUser(2, inputUser);
    }
}
