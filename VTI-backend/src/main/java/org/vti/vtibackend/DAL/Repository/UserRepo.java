package org.vti.vtibackend.DAL.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vti.vtibackend.DAL.Entity.User;


public interface UserRepo extends JpaRepository<User, Integer> {
}
