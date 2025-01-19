package org.vti.vtibackend.DAL.Implementation;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.vti.vtibackend.BLL.Interface.IUserDAL;
import org.vti.vtibackend.DAL.Entity.User;
import org.vti.vtibackend.DAL.Mapper.UserMapper;
import org.vti.vtibackend.DAL.Repository.UserRepo;
import org.vti.vtibackend.model.User.UserDTO;
import org.vti.vtibackend.model.User.UserInfo;

import java.util.List;
import java.util.Optional;
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
    public List<UserInfo> findAll() {
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

    @Override
    public List<UserDTO> getUsernamesAndRoles() {
        return userRepo.getUsernamesAndRoles()
                .stream()
                .map(row -> new UserDTO(
                        ((Number) row[0]).longValue(), // user_id
                        (String) row[1],              // username
                        (String) row[2]               // role
                ))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO UpdateUser(int user_id, UserInfo user) {
        Optional<User> optionalUser = userRepo.findById(user_id);
        if (optionalUser.isPresent()) {
            User userToUpdate = optionalUser.get();
            userToUpdate.setUsername(user.getUsername());
            userToUpdate.setRole(user.getRole());
            User updatedUser = userRepo.save(userToUpdate);
            return new UserDTO(updatedUser.getUser_id(), updatedUser.getUsername(), updatedUser.getRole());
        } else {
            throw new EntityNotFoundException("Gebruiker met ID " + user_id + " niet gevonden");
        }
    }

}
