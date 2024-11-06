package org.vti.vtibackend.DAL.Interface;

import org.vti.vtibackend.DAL.Entity.User;

import java.util.List;

public interface IUserDAL {
    User save(User user);
    List<User> findAll();
}
