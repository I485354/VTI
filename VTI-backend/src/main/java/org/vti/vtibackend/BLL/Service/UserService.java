package org.vti.vtibackend.BLL.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vti.vtibackend.BLL.Interface.IUserService;
import org.vti.vtibackend.BLL.Mapper.UserMapper;
import org.vti.vtibackend.DAL.Entity.User;
import org.vti.vtibackend.DAL.Interface.IUserDAL;
import org.vti.vtibackend.DAL.Repository.UserRepo;
import org.vti.vtibackend.model.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    private final IUserDAL userDAL;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper, IUserDAL userDAL) {
        this.userMapper = userMapper;
        this.userDAL = userDAL;
    }

    public List<UserDTO> getAllUsers() {
        List<User> users =  userDAL.findAll();
        return users.stream()
                .map(userMapper::ToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO createUser(UserDTO users) {
        User user = userMapper.ToEntity(users);
        User savedUser = userDAL.save(user);
        return userMapper.ToDTO(savedUser);
    }
}