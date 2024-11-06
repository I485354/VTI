package org.vti.vtibackend.DAL.Interface;

import org.vti.vtibackend.DAL.Entity.Customer;
import org.vti.vtibackend.model.CustomerDTO;

import java.util.List;
import java.util.Optional;

public interface ICustomerDAL {
    Customer save(Customer customer);
    List<Customer> findAll();
    Optional<Customer> findById(int id);
    void deleteById(int id);

}
