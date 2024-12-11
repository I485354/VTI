package org.vti.vtibackend.DAL.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.vti.vtibackend.DAL.Entity.Customer;
import org.vti.vtibackend.model.Customer.CustomerDTO;
import org.vti.vtibackend.model.Customer.CustomerInfoDTO;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @Mapping(source = "customer_id", target = "customer_id")
    CustomerDTO ToDTO(Customer customer);

    @Mapping(source = "customer_number", target = "customer_number")
    CustomerInfoDTO ToInfoDTO(Customer customer);

    @Mapping(source = "customer_id", target = "customer_id")
    Customer ToEntity(CustomerDTO customerDTO);
}
