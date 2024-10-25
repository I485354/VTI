package org.vti.vtibackend.DAL.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;
import org.vti.vtibackend.model.Users;

public interface User extends JpaRepository<Users, Integer> {
}
