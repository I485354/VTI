package org.vti.vtibackend.BLL.Interface;

import org.vti.vtibackend.model.User.UserDTO;

import java.util.List;

public interface IUserDAL {
    UserDTO save(UserDTO userDTO);
    List<UserDTO> findAll();
}
