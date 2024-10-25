package org.vti.vtibackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long customer_id;
    private String Name;
    private String Address;
    private String Phone;
    private String Email;
    private String Company;

    public Long getId() {
        return customer_id;
    }
    public void setId(int customer_id) {
        this.customer_id = customer_id;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        this.Name = name;
    }
    public String getAddress() {
        return Address;
    }
    public void setAddress(String address) {
        this.Address = address;
    }
    public String getPhone() {
        return Phone;
    }
    public void setPhone(String phone) {
        this.Phone = phone;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        this.Email = email;
    }
    public String getCompany() {
        return Company;
    }
    public void setCompany(String company) {
        Company = company;
    }

    public void setId(Long id) {
        this.customer_id = id;
    }


}