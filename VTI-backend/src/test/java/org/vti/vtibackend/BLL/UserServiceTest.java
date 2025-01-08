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
        createUser = new CreateUserDTO("jane_doe", "securePass");
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
        // Arrange
        // userDAL.findByUsername(...) => null (dus gebruiker bestaat niet)
        when(userDAL.findByUsername(createUser.getUsername())).thenReturn(null);

        // passwordEncoder.encode(...) => "encodedPassword"
        when(passwordEncoder.encode("securePass")).thenReturn("encodedPassword");

        // Stel in wat userDAL.save(...) moet retourneren
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
        // Arrange
        // Doe alsof userDAL.findByUsername(...) een bestaande user teruggeeft
        UserDTO existing = new UserDTO(10L, "jane_doe", "someEncodedPass", "admin");
        when(userDAL.findByUsername("jane_doe")).thenReturn(existing);

        // Act & Assert
        assertThatThrownBy(() -> userService.createUser(createUser))
                .isInstanceOf(BadCredentialsException.class)
                .hasMessage("Username already exists");

        // verify dat er GEEN save is aangeroepen
        verify(userDAL, never()).save(any());
    }

    @Test
    void createUser_NullInput() {
        // Act & Assert
        assertThatThrownBy(() -> userService.createUser(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("User cannot be null");

        // verify dat er geen interactie is met save
        verify(userDAL, never()).save(any());
    }
}
