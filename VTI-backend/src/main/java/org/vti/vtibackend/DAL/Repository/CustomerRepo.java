package org.vti.vtibackend.DAL.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vti.vtibackend.DAL.Entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
}
