package org.vti.vtibackend.DAL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.vti.vtibackend.DAL.Entity.User;
import org.vti.vtibackend.DAL.Implementation.UserDAL;
import org.vti.vtibackend.DAL.Repository.UserRepo;
import org.vti.vtibackend.model.User.*;
import org.vti.vtibackend.DAL.Mapper.UserMapper;
import jakarta.persistence.EntityNotFoundException;




import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserDALTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserDAL userDAL;

    private User userEntity;
    private UserDTO userDTO;
    private UserInfo userInfo;

    @BeforeEach
    void setUp() {
        // Voorbeeld-data
        userEntity = new User();
        userEntity.setUser_id(1L);
        userEntity.setUsername("john_doe");
        userEntity.setPassword("encodedPass");
        userEntity.setRole("ADMIN");

        userDTO = new UserDTO(1L, "john_doe", "encodedPass", "ADMIN");
        userInfo = new UserInfo(1L, "john_doe", "ADMIN");
    }

    @Test
    void testSave() {
        // Arrange
        when(userMapper.ToEntity(userDTO)).thenReturn(userEntity);
        when(userRepo.save(userEntity)).thenReturn(userEntity);
        when(userMapper.ToDTO(userEntity)).thenReturn(userDTO);

        // Act
        UserDTO result = userDAL.save(userDTO);

        // Assert
        assertNotNull(result);
        assertEquals("john_doe", result.getUsername());
        verify(userMapper).ToEntity(userDTO);
        verify(userRepo).save(userEntity);
        verify(userMapper).ToDTO(userEntity);
    }

    @Test
    void testFindAll() {
        // Arrange
        // Stel je repo geeft een lijst van Users terug
        List<User> userList = Collections.singletonList(userEntity);
        when(userRepo.findAll()).thenReturn(userList);

        // Mock de mapping naar UserInfo
        when(userMapper.ToUserInfo(userEntity)).thenReturn(userInfo);

        // Act
        List<UserInfo> allUsers = userDAL.findAll();

        // Assert
        assertEquals(1, allUsers.size());
        assertEquals("john_doe", allUsers.get(0).getUsername());
        verify(userRepo).findAll();
        verify(userMapper).ToUserInfo(userEntity);
    }

    @Test
    void testGetUsernamesAndRoles() {
        // Arrange
        // Stel je repo geeft een lijst van Object[] terug
        List<Object[]> rawData = new ArrayList<>();
        rawData.add(new Object[]{1, "john_doe", "ADMIN"});
        rawData.add(new Object[]{2, "jane_doe", "USER"});

        when(userRepo.getUsernamesAndRoles()).thenReturn(rawData);

        // Act
        List<UserDTO> result = userDAL.getUsernamesAndRoles();

        // Assert
        assertEquals(2, result.size());
        assertEquals("john_doe", result.get(0).getUsername());
        assertEquals("jane_doe", result.get(1).getUsername());
        verify(userRepo).getUsernamesAndRoles();
    }

    @Test
    void testUpdateUser_Found() {
        // Arrange
        int userId = 1;
        UserInfo updatedInfo = new UserInfo(1, "new_username", "USER");
        Optional<User> optionalUser = Optional.of(userEntity);

        when(userRepo.findById(userId)).thenReturn(optionalUser);
        // Mock de save
        User updatedEntity = new User();
        updatedEntity.setUser_id(1L);
        updatedEntity.setUsername("new_username");
        updatedEntity.setRole("USER");

        when(userRepo.save(any(User.class))).thenReturn(updatedEntity);

        // Act
        UserDTO result = userDAL.UpdateUser(userId, updatedInfo);

        // Assert
        assertNotNull(result);
        assertEquals("new_username", result.getUsername());
        assertEquals("USER", result.getRole());
        verify(userRepo).findById(userId);
        verify(userRepo).save(any(User.class));
    }

    @Test
    void testUpdateUser_NotFound() {
        // Arrange
        int userId = 999;
        when(userRepo.findById(userId)).thenReturn(Optional.empty());
        UserInfo updatedInfo = new UserInfo(999, "missing_user", "ADMIN");

        // Act & Assert
        assertThrows(jakarta.persistence.EntityNotFoundException.class, () ->
                userDAL.UpdateUser(userId, updatedInfo)
        );
        verify(userRepo).findById(userId);
    }
}
