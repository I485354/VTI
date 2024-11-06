package org.vti.vtibackend.DAL.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.vti.vtibackend.DAL.Entity.User;
import org.vti.vtibackend.DAL.Interface.IUserDAL;
import org.vti.vtibackend.DAL.Repository.UserRepo;

import java.util.List;

@Repository
public class UserDAL implements IUserDAL {

    private final UserRepo userRepo;

    @Autowired
    public UserDAL(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User save(User user) {
        return userRepo.save(user);
    }

    @Override
    public List<User> findAll(){
        return userRepo.findAll();
    }

}
