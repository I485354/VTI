package org.vti.vtibackend.BLL.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.vti.vtibackend.BLL.Interface.ICustomerDAL;
import org.vti.vtibackend.model.CreateCustomerDTO;
import org.vti.vtibackend.model.CustomerDTO;
import org.vti.vtibackend.model.UpdatedCustomerDTO;

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


    public CustomerDTO createCustomer(CreateCustomerDTO createCustomerDTO) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName(createCustomerDTO.getName());
        customerDTO.setEmail(createCustomerDTO.getEmail());
        customerDTO.setPhone(createCustomerDTO.getPhone());
        customerDTO.setAddress(createCustomerDTO.getAddress());
        customerDTO.setCompany(createCustomerDTO.getCompany());
        int highestCustomerNumber = customerDAL.findHighestCustomerNumber();
        int nextCustomerNumber = (highestCustomerNumber != 0) ? highestCustomerNumber + 1 : 1;
        customerDTO.setCustomer_number(nextCustomerNumber);
        return customerDAL.createCustomer(customerDTO);
    }

    public CustomerDTO updateCustomer(int id, UpdatedCustomerDTO updatedCustomerDTO) {
        // Haal de bestaande klant op via de DAL
        CustomerDTO existingCustomerDTO = customerDAL.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Update de relevante velden van de bestaande klant met de nieuwe gegevens
        existingCustomerDTO.setName(updatedCustomerDTO.getName());
        existingCustomerDTO.setCompany(updatedCustomerDTO.getCompany());
        existingCustomerDTO.setEmail(updatedCustomerDTO.getEmail());
        existingCustomerDTO.setPhone(updatedCustomerDTO.getPhone());
        existingCustomerDTO.setAddress(updatedCustomerDTO.getAddress());

        // Roep de DAL aan om de geüpdatete klant op te slaan
        return customerDAL.updateCustomer(id, existingCustomerDTO);
    }


    public void deleteCustomer(int id) {
        customerDAL.deleteById(id);
    }
}