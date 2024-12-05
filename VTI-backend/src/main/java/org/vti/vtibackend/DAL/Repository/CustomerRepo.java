package org.vti.vtibackend.DAL.Repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.vti.vtibackend.DAL.Entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    @Query("SELECT MAX(c.customer_number) FROM Customer c")
    int findHighestCustomerNumber();

}
