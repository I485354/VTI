package org.vti.vtibackend.BLL.Interface;

import org.vti.vtibackend.model.Customer.CustomerDTO;
import org.vti.vtibackend.model.Customer.CustomerInfoDTO;

import java.util.List;
import java.util.Optional;

public interface ICustomerDAL {
    CustomerDTO save(CustomerDTO customer);
    CustomerDTO createCustomer(CustomerDTO customerDTO);
    CustomerDTO updateCustomer(int id, CustomerDTO CustomerDTO);
    List<CustomerDTO> findAll();
    Optional<CustomerDTO> findById(int id);
    void deleteById(int id);
    int findHighestCustomerNumber();
    List<CustomerInfoDTO> findCustomerInfo();
    CustomerInfoDTO findCustomerInfoById(int id);

}
