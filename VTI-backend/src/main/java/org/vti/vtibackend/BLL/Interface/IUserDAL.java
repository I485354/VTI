package org.vti.vtibackend.BLL.Interface;

import org.vti.vtibackend.DAL.Entity.User;
import org.vti.vtibackend.model.UserDTO;

import java.util.List;

public interface IUserDAL {
    UserDTO save(UserDTO userDTO);
    List<UserDTO> findAll();
}
