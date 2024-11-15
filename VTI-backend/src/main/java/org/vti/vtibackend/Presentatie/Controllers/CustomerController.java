package org.vti.vtibackend.Presentatie.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.vti.vtibackend.BLL.Interface.ICustomerService;
import org.vti.vtibackend.model.CustomerDTO;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final ICustomerService customerService;

    @Autowired
    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PostMapping
    public CustomerDTO createCustomer(@RequestBody CustomerDTO customers) {
        return customerService.createCustomer(customers);
    }
    @PutMapping("/{id}")
    public CustomerDTO updateCustomer(@PathVariable int id, @RequestBody CustomerDTO customerDTO) {
        return customerService.updateCustomer(id, customerDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id);
    }
}
