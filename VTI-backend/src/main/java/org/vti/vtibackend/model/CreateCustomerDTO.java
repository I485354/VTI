package org.vti.vtibackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerDTO {
    private String name;
    private String company;
    private String address;
    private String email;
    private String phone;
}
