package org.vti.vtibackend.BLL.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.vti.vtibackend.BLL.Interface.ICustomerDAL;
import org.vti.vtibackend.model.CustomerDTO;

import java.util.List;


@Service
public class CustomerService {
    private final ICustomerDAL customerDAL;


    @Autowired
    public CustomerService( ICustomerDAL customerDAL) {
        this.customerDAL = customerDAL;
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerDAL.findAll();
    }


    public CustomerDTO createCustomer(CustomerDTO customers) {
        return customerDAL.save(customers);
    }

    public CustomerDTO updateCustomer(int id, CustomerDTO customerDTO) {
        CustomerDTO existingCustomerDTO = customerDAL.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Update de velden van de bestaande klant met gegevens uit de nieuwe DTO
        existingCustomerDTO.setName(customerDTO.getName());
        existingCustomerDTO.setEmail(customerDTO.getEmail());
        existingCustomerDTO.setCompany(customerDTO.getCompany());
        existingCustomerDTO.setPhone(customerDTO.getPhone());
        existingCustomerDTO.setAddress(customerDTO.getAddress());

        // Sla de geüpdatete klant op via de DAL
        return customerDAL.save(existingCustomerDTO);
    }


    public void deleteCustomer(int id) {
        customerDAL.deleteById(id);
    }
}