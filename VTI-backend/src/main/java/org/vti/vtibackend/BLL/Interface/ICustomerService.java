package org.vti.vtibackend.BLL.Interface;

import org.vti.vtibackend.model.CustomerDTO;
import java.util.List;

public interface ICustomerService {
    List<CustomerDTO> getAllCustomers();
    CustomerDTO createCustomer(CustomerDTO customers);
}
