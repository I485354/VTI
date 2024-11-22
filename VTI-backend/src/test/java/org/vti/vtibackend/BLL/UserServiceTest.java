package org.vti.vtibackend.BLL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.vti.vtibackend.BLL.Mapper.UserMapper;
import org.vti.vtibackend.BLL.Service.UserService;
import org.vti.vtibackend.DAL.Entity.User;
import org.vti.vtibackend.DAL.Implementation.UserDAL;
import org.vti.vtibackend.model.UserDTO;


import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
public class UserServiceTest {

    @Mock
    private UserDAL userDAL;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    private User user1;
    private User user2;

    private UserDTO userDTO1;
    private UserDTO userDTO2;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user1 = new User(1L, "C123", "1234", "customer" );
        user2 = new User(1L, "M123", "4321", "Manager" );
        userDTO1 = new UserDTO(1L, "C123", "1234", "customer" );
        userDTO2 = new UserDTO(1L, "M123", "4321", "Manager" );
    }

    @Test
    void testGetAllUsers() {
        // Arrange
        when(userDAL.findAll()).thenReturn(Arrays.asList(user1, user2));
        when(userMapper.ToDTO(user1)).thenReturn(userDTO1);
        when(userMapper.ToDTO(user2)).thenReturn(userDTO2);

        List<UserDTO> users = userService.getAllUsers();

        assertThat(users).contains(userDTO1, userDTO2);

        verify(userMapper, times(1)).ToDTO(user1);

    }
}
