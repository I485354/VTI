package org.vti.vtibackend.BLL.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vti.vtibackend.DAL.Repository.User;
import org.vti.vtibackend.model.Users;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private User userRepository;

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Users createUser(Users users) {
        return userRepository.save(users);
    }
}