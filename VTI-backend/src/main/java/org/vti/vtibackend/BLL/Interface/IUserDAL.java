package org.vti.vtibackend.BLL.Interface;

import org.vti.vtibackend.model.User.UserDTO;
import org.vti.vtibackend.model.User.UserInfo;

import java.util.List;

public interface IUserDAL {
    UserDTO save(UserDTO userDTO);
    List<UserInfo> findAll();
    UserDTO findByUsername(String username);
    UserDTO authenticateUser(String username, String password);
    List<UserDTO> getUsernamesAndRoles();
    UserDTO UpdateUser(int user_id, UserInfo user);
}
