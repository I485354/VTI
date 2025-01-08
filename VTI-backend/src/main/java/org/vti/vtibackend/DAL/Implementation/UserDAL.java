package org.vti.vtibackend.DAL.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.vti.vtibackend.DAL.Entity.User;
import org.vti.vtibackend.BLL.Interface.IUserDAL;
import org.vti.vtibackend.DAL.Mapper.UserMapper;
import org.vti.vtibackend.DAL.Repository.UserRepo;
import org.vti.vtibackend.model.User.UserDTO;
import org.vti.vtibackend.model.User.UserInfo;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserDAL implements IUserDAL {

    private final UserRepo userRepo;
    private final UserMapper userMapper;
    @Autowired
    public UserDAL(UserRepo userRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        User user = userMapper.ToEntity(userDTO);
        User savedUser = userRepo.save(user);
        return userMapper.ToDTO(savedUser);
    }

    @Override
    public List<UserInfo> findAll(){
        return userRepo.findAll()
                .stream()
                .map(userMapper::ToUserInfo)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUsername(String username) {

        return userRepo.findByUsername(username).stream()
                .findFirst()
                .map(userMapper::ToDTO)
                .orElse(null);
    }

    @Override
    public UserDTO authenticateUser(String username, String password) {
        return userRepo.findByUsername(username)
                .stream().findFirst()
                .map(userMapper::ToDTO)
                .orElseThrow(() -> new UsernameNotFoundException("gebruiker niet gevonden"));
    }

}
