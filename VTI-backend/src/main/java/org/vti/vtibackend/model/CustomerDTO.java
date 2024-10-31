package org.vti.vtibackend.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    private int customer_id;
    private String name;
    private String company;
    private String address;
    private String email;
    private String phone;

}