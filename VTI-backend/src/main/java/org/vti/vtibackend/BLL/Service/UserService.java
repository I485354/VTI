package org.vti.vtibackend.BLL.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.vti.vtibackend.BLL.Interface.IUserDAL;
import org.vti.vtibackend.model.User.CreateUserDTO;
import org.vti.vtibackend.model.User.UserDTO;
import org.vti.vtibackend.model.User.UserInfo;

import java.util.List;


@Service
public class UserService  {

    private final IUserDAL userDAL;

    private final PasswordEncoder passwordEncoder;




    @Autowired
    public UserService(IUserDAL userDAL, PasswordEncoder passwordEncoder) {
        this.userDAL = userDAL;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserInfo> getAllUsers() {
        return userDAL.findAll();
    }

    public UserDTO createUser(CreateUserDTO users) {
        if (users == null) {
            throw new NullPointerException("User cannot be null");
        } else {
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(users.getUsername());
            userDTO.setPassword(passwordEncoder.encode(users.getPassword()));
            userDTO.setRole("admin");
            users.setPassword(passwordEncoder.encode(users.getPassword()));
            return userDAL.save(userDTO);
        }
    }
    public UserDTO findByUsername(String username) {
        return userDAL.findByUsername(username);
    }

    public UserDTO authenticate(String username, String password) {
        UserDTO userDTO = userDAL.authenticateUser(username, passwordEncoder.encode(password));
        if (!passwordEncoder.matches(password, userDTO.getPassword())) {
            throw new BadCredentialsException("Ongeldige inloggegevens");
        }
        return new UserDTO(userDTO.getUser_id(), userDTO.getUsername(), userDTO.getRole());
    }
}