package org.vti.vtibackend.Presentatie.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.vti.vtibackend.BLL.Service.CustomerService;
import org.vti.vtibackend.model.CreateCustomerDTO;
import org.vti.vtibackend.model.CustomerDTO;
import org.vti.vtibackend.model.UpdatedCustomerDTO;

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
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PostMapping
    public CustomerDTO createCustomer(@RequestBody CreateCustomerDTO customers) {
        return customerService.createCustomer(customers);
    }
    @PutMapping("/{id}")
    public CustomerDTO updateCustomer(@PathVariable int id, @RequestBody UpdatedCustomerDTO updatedCustomers) {
        return customerService.updateCustomer(id, updatedCustomers);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id);
    }
}
