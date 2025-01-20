package org.vti.vtibackend.DAL.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.vti.vtibackend.DAL.Entity.User;

import java.util.List;
import java.util.Optional;


public interface UserRepo extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u where u.username = :username")
    Optional<User> findByUsername(String username);

    @Query("SELECT u.user_id, u.username, u.role, u.customer_id FROM User u")
    List<Object[]> getUsernamesAndRoles();


}
