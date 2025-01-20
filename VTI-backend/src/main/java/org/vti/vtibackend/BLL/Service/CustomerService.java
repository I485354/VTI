package org.vti.vtibackend.BLL.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vti.vtibackend.BLL.Interface.ICustomerDAL;
import org.vti.vtibackend.model.Customer.CreateCustomerDTO;
import org.vti.vtibackend.model.Customer.CustomerDTO;
import org.vti.vtibackend.model.Customer.CustomerInfoDTO;
import org.vti.vtibackend.model.Customer.UpdatedCustomerDTO;

import java.util.List;


@Service
public class CustomerService {
    private final ICustomerDAL customerDAL;


    @Autowired
    public CustomerService(ICustomerDAL customerDAL) {
        this.customerDAL = customerDAL;
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerDAL.findAll();
    }

    public CustomerDTO getCustomerById(int id) {
        return customerDAL.findById(id)
                .orElseThrow(() -> new RuntimeException("no Customer found with id " + id));
    }


    public CustomerDTO createCustomer(CreateCustomerDTO createCustomerDTO) {
        if (createCustomerDTO.getName() == null || createCustomerDTO.getName().isEmpty()
                || createCustomerDTO.getEmail() == null || createCustomerDTO.getEmail().isEmpty()
                || createCustomerDTO.getPhone() == null || createCustomerDTO.getPhone().isEmpty()
                || createCustomerDTO.getAddress() == null || createCustomerDTO.getAddress().isEmpty()
                || createCustomerDTO.getCompany() == null || createCustomerDTO.getCompany().isEmpty()) {
            throw new IllegalArgumentException("Alle velden (name, email, phone, address, company) zijn verplicht.");
        }
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName(createCustomerDTO.getName());
        customerDTO.setEmail(createCustomerDTO.getEmail());
        customerDTO.setPhone(createCustomerDTO.getPhone());
        customerDTO.setAddress(createCustomerDTO.getAddress());
        customerDTO.setCompany(createCustomerDTO.getCompany());

        int highestCustomerNumber = 0;
        try {
            highestCustomerNumber = customerDAL.findHighestCustomerNumber();
        } catch (Exception e) {
            System.err.println("Kan het hoogste klantnummer niet ophalen: " + e.getMessage());
        }


        int nextCustomerNumber = (highestCustomerNumber > 0) ? highestCustomerNumber + 1 : 1;
        customerDTO.setCustomer_number(nextCustomerNumber);
        return customerDAL.createCustomer(customerDTO);
    }

    public CustomerDTO updateCustomer(int id, UpdatedCustomerDTO updatedCustomerDTO) {
        CustomerDTO existingCustomerDTO = customerDAL.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        existingCustomerDTO.setName(updatedCustomerDTO.getName());
        existingCustomerDTO.setCompany(updatedCustomerDTO.getCompany());
        existingCustomerDTO.setEmail(updatedCustomerDTO.getEmail());
        existingCustomerDTO.setPhone(updatedCustomerDTO.getPhone());
        existingCustomerDTO.setAddress(updatedCustomerDTO.getAddress());
        return customerDAL.updateCustomer(id, existingCustomerDTO);
    }


    public void deleteCustomer(int id) {
        customerDAL.deleteById(id);
    }

    public List<CustomerInfoDTO> getCustomerInfo() {
        return customerDAL.findCustomerInfo();
    }

    public CustomerInfoDTO getCustomerInfoById(int id) {
        return customerDAL.findCustomerInfoById(id);
    }
}