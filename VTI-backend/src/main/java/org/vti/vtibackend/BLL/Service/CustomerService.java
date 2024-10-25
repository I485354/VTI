package org.vti.vtibackend.BLL.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vti.vtibackend.DAL.Repository.Customer;
import org.vti.vtibackend.model.Customers;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private Customer customerRepository;

    public List<Customers> getAllCustomers() {
        return customerRepository.findAll();
    }


    public Customers createCustomer(Customers customers) {
        return customerRepository.save(customers);
    }
}