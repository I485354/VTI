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


import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
public class UserServiceTest {

    @Mock
    private UserDAL userDAL;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        // Arrange
        User user1 = new User(1L, "C123", "1234", "customer" );
        User user2 = new User(1L, "M123", "4321", "Manager" );

        when(userDAL.findAll()).thenReturn(Arrays.asList(user1, user2));
    }
}
