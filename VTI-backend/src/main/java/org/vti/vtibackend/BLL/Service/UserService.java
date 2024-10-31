package org.vti.vtibackend.BLL.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vti.vtibackend.BLL.Mapper.UserMapper;
import org.vti.vtibackend.DAL.Entity.User;
import org.vti.vtibackend.DAL.Repository.UserRepo;
import org.vti.vtibackend.model.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepo userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepo userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDTO> getAllUsers() {
        List<User> users =  userRepository.findAll();
        return users.stream()
                .map(userMapper::ToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO createUser(UserDTO users) {
        User user = userMapper.ToEntity(users);
        User savedUser = userRepository.save(user);
        return userMapper.ToDTO(savedUser);
    }
}