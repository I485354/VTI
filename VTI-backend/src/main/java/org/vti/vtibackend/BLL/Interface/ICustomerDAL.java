package org.vti.vtibackend.BLL.Interface;

import org.vti.vtibackend.DAL.Entity.Customer;
import org.vti.vtibackend.model.CustomerDTO;

import java.util.List;
import java.util.Optional;

public interface ICustomerDAL {
    CustomerDTO save(CustomerDTO customer);
    List<CustomerDTO> findAll();
    Optional<CustomerDTO> findById(int id);
    void deleteById(int id);

}
