package org.vti.vtibackend.DAL.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.vti.vtibackend.DAL.Entity.Customer;
import org.vti.vtibackend.BLL.Interface.ICustomerDAL;
import org.vti.vtibackend.DAL.Mapper.CustomerMapper;
import org.vti.vtibackend.DAL.Repository.CustomerRepo;
import org.vti.vtibackend.model.CustomerDTO;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CustomerDAL implements ICustomerDAL {

    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerDAL(CustomerRepo customerRepo, CustomerMapper customerMapper) {
        this.customerRepo = customerRepo;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        Customer customer = customerMapper.ToEntity(customerDTO);
        Customer savedCustomer = customerRepo.save(customer);
        return customerMapper.ToDTO(savedCustomer);
    }

    @Override
    public List<CustomerDTO> findAll() {
        return customerRepo.findAll()
                .stream()
                .map(customerMapper::ToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CustomerDTO> findById(int id) {
        return customerRepo.findById(id)
                .map(customerMapper::ToDTO);
    }

    @Override
    public void deleteById(int id) {
        customerRepo.deleteById(id);
    }

}
