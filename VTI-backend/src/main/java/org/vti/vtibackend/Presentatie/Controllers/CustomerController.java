package org.vti.vtibackend.Presentatie.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.vti.vtibackend.BLL.Service.CustomerService;
import org.vti.vtibackend.model.Customer.CreateCustomerDTO;
import org.vti.vtibackend.model.Customer.CustomerDTO;
import org.vti.vtibackend.model.Customer.CustomerInfoDTO;
import org.vti.vtibackend.model.Customer.UpdatedCustomerDTO;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> newCustomer = customerService.getAllCustomers();
        return new ResponseEntity<>(newCustomer, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CreateCustomerDTO customers) {
        CustomerDTO createdCustomer = customerService.createCustomer(customers);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable int id, @RequestBody UpdatedCustomerDTO updatedCustomers) {
        CustomerDTO customerDTO = customerService.updateCustomer(id, updatedCustomers);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id);
    }
    @GetMapping("/info")
    public ResponseEntity<List<CustomerInfoDTO>> getCustomerInfo() {
       List<CustomerInfoDTO> customerInfo = customerService.getCustomerInfo();
        return ResponseEntity.ok(customerInfo);
    }
}
