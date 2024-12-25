package org.vti.vtibackend.BLL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
    void createUser() {
        // Arrange
        when(passwordEncoder.encode("securePass")).thenReturn("encodedPassword");
        when(userDAL.save(any(UserDTO.class))).thenReturn(user1);

        ArgumentCaptor<UserDTO> captor = ArgumentCaptor.forClass(UserDTO.class);

        // Act
        UserDTO createdUser = userService.createUser(createUser);

        // Assert
        assertThat(createdUser).isNotNull();
        assertThat(createdUser.getUser_id()).isEqualTo(1);
        assertThat(createdUser.getUsername()).isEqualTo("john_doe");
        assertThat(createdUser.getRole()).isEqualTo("admin");

        // Capture het object dat is doorgegeven aan save
        verify(userDAL, times(1)).save(captor.capture());
        UserDTO capturedUser = captor.getValue();

        // Controleer de waarden van het captured object
        assertThat(capturedUser.getUsername()).isEqualTo("jane_doe");
        assertThat(capturedUser.getPassword()).isEqualTo("encodedPassword");
    }


    @Test
    void createUser_NullInput() {
        // Act & Assert
        assertThatThrownBy(() -> userService.createUser(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("User cannot be null");

        verify(userDAL, never()).save(any());
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
}
