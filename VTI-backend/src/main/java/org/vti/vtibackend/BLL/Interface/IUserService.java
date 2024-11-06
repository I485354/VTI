package org.vti.vtibackend.BLL.Interface;

import org.vti.vtibackend.DAL.Entity.User;
import org.vti.vtibackend.model.UserDTO;

import java.util.List;

public interface IUserService {
    List<UserDTO> getAllUsers();
    UserDTO createUser(UserDTO users);
}
