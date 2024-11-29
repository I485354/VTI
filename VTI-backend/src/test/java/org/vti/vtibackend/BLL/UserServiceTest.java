package org.vti.vtibackend.BLL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.vti.vtibackend.BLL.Interface.IUserDAL;
import org.vti.vtibackend.DAL.Mapper.UserMapper;
import org.vti.vtibackend.BLL.Service.UserService;
import org.vti.vtibackend.DAL.Entity.User;
import org.vti.vtibackend.DAL.Implementation.UserDAL;
import org.vti.vtibackend.model.UserDTO;


import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;
public class UserServiceTest {

    @Mock
    private IUserDAL userDAL;

    @InjectMocks
    private UserService userService;

    private UserDTO user1;
    private UserDTO user2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user1 = new UserDTO(1, "john_doe", "password123", "admin");
        user2 = new UserDTO(2, "jane_doe", "securePass", "user");
    }

    @Test
    void getAllUsers() {
        // Arrange
        when(userDAL.findAll()).thenReturn(Arrays.asList(user1, user2));

        // Act
        List<UserDTO> users = userService.getAllUsers();

        // Assert
        assertThat(users).hasSize(2);
        assertThat(users.get(0)).isEqualTo(user1);
        assertThat(users.get(1)).isEqualTo(user2);

        verify(userDAL, times(1)).findAll();
    }

    @Test
    void createUser() {
        // Arrange
        when(userDAL.save(user1)).thenReturn(user1);

        // Act
        UserDTO createdUser = userService.createUser(user1);

        // Assert
        assertThat(createdUser).isNotNull();
        assertThat(createdUser.getUser_id()).isEqualTo(1);
        assertThat(createdUser.getUsername()).isEqualTo("john_doe");
        assertThat(createdUser.getRole()).isEqualTo("admin");

        verify(userDAL, times(1)).save(user1);
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
        List<UserDTO> users = userService.getAllUsers();

        // Assert
        assertThat(users).isEmpty();
        verify(userDAL, times(1)).findAll();
    }
}
