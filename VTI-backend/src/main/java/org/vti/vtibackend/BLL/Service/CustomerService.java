package org.vti.vtibackend.BLL.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vti.vtibackend.BLL.Mapper.CustomerMapper;
import org.vti.vtibackend.DAL.Entity.Customer;
import org.vti.vtibackend.DAL.Repository.CustomerRepo;
import org.vti.vtibackend.model.CustomerDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepo customerRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerService(CustomerRepo customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customerMapper::ToDTO)
                .collect(Collectors.toList());
    }


    public CustomerDTO createCustomer(CustomerDTO customers) {
        Customer customer = customerMapper.ToEntity(customers);
        Customer savedCustomer= customerRepository.save(customer);
        return customerMapper.ToDTO(savedCustomer);
    }
}