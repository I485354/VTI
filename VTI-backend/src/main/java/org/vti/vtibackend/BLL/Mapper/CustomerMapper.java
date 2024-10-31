package org.vti.vtibackend.BLL.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.vti.vtibackend.DAL.Entity.Customer;
import org.vti.vtibackend.model.CustomerDTO;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @Mapping(source = "customer_id", target = "customer_id")
    CustomerDTO ToDTO(Customer customer);

    @Mapping(source = "customer_id", target = "customer_id")
    Customer ToEntity(CustomerDTO customerDTO);
}
