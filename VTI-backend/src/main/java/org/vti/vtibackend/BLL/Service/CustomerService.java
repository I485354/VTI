package org.vti.vtibackend.BLL.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vti.vtibackend.BLL.Interface.ICustomerService;
import org.vti.vtibackend.BLL.Mapper.CustomerMapper;
import org.vti.vtibackend.DAL.Entity.Customer;
import org.vti.vtibackend.DAL.Interface.ICustomerDAL;
import org.vti.vtibackend.model.CustomerDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService implements ICustomerService {
    private final ICustomerDAL customerDAL;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerService( CustomerMapper customerMapper, ICustomerDAL customerDAL) {
        this.customerMapper = customerMapper;
        this.customerDAL = customerDAL;
    }

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerDAL.findAll();
        return customers.stream()
                .map(customerMapper::ToDTO)
                .collect(Collectors.toList());
    }


    public CustomerDTO createCustomer(CustomerDTO customers) {
        Customer customer = customerMapper.ToEntity(customers);
        Customer savedCustomer= customerDAL.save(customer);
        return customerMapper.ToDTO(savedCustomer);
    }
}