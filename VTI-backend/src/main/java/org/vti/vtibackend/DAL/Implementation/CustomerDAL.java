package org.vti.vtibackend.DAL.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.vti.vtibackend.DAL.Entity.Customer;
import org.vti.vtibackend.DAL.Interface.ICustomerDAL;
import org.vti.vtibackend.DAL.Repository.CustomerRepo;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerDAL implements ICustomerDAL {

    private final CustomerRepo customerRepo;

    @Autowired
    public CustomerDAL(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepo.save(customer);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepo.findAll();
    }

    @Override
    public Optional<Customer> findById(int id) {
        return customerRepo.findById(id);
    }

    @Override
    public void deleteById(int id) {
        customerRepo.deleteById(id);
    }

    @Override
    public boolean existsById(int id) {
        return customerRepo.existsById(id);
    }
}
