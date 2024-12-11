package org.vti.vtibackend.BLL.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.vti.vtibackend.BLL.Interface.IUserDAL;
import org.vti.vtibackend.model.User.UserDTO;

import java.util.List;


@Service
public class UserService  {

    private final IUserDAL userDAL;


    @Autowired
    public UserService(IUserDAL userDAL) {
        this.userDAL = userDAL;
    }

    public List<UserDTO> getAllUsers() {
        return userDAL.findAll();
    }

    public UserDTO createUser(UserDTO users) {
     return userDAL.save(users);
    }
}