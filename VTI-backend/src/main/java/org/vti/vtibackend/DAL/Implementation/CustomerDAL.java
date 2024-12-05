package org.vti.vtibackend.DAL.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.vti.vtibackend.DAL.Entity.Customer;
import org.vti.vtibackend.BLL.Interface.ICustomerDAL;
import org.vti.vtibackend.DAL.Mapper.CustomerMapper;
import org.vti.vtibackend.DAL.Repository.CustomerRepo;
import org.vti.vtibackend.model.CustomerDTO;
import org.vti.vtibackend.model.UpdatedCustomerDTO;


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
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.ToEntity(customerDTO);
        Customer savedCustomer = customerRepo.save(customer);
        return customerMapper.ToDTO(savedCustomer);
    }

    @Override
    public CustomerDTO updateCustomer(int id, CustomerDTO customerDTO) {
        // Haal de bestaande klant op
        Customer existingCustomer = customerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Update de velden van de entiteit
        existingCustomer.setName(customerDTO.getName());
        existingCustomer.setCompany(customerDTO.getCompany());
        existingCustomer.setEmail(customerDTO.getEmail());
        existingCustomer.setPhone(customerDTO.getPhone());
        existingCustomer.setAddress(customerDTO.getAddress());

        // Sla de wijzigingen op
        Customer updatedCustomer = customerRepo.save(existingCustomer);

        // Map de entiteit terug naar een DTO
        return customerMapper.ToDTO(updatedCustomer);
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

    @Override
    public int findHighestCustomerNumber(){
        return customerRepo.findHighestCustomerNumber();
    }

}
