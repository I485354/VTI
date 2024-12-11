package org.vti.vtibackend.DAL.Entity;

import jakarta.persistence.*;


@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customer_id;
    private String name;
    private String company;
    private String address;
    private String email;
    private String phone;
    private int customer_number;

    public Customer() {}

    public Customer(int customer_id,String name, String company, String address, String email, String phone, int customer_number) {
        this.customer_id = customer_id;
        this.name = name;
        this.company = company;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.customer_number = customer_number;

    }

    public int getCustomer_id() {
        return this.customer_id;
    }


    public String getName() {
        return this.name;
    }


    public String getCompany() {
        return this.company;
    }


    public String getAddress() {
        return this.address;
    }


    public String getEmail() {
        return this.email;
    }


    public String getPhone() {
        return this.phone;
    }

    public int getCustomer_number() {
        return this.customer_number;
    }


    public void setCustomer_id(final int customer_id) {
        this.customer_id = customer_id;
    }

    public void setName(final String name) {
        this.name = name;
    }


    public void setCompany(final String company) {
        this.company = company;
    }


    public void setAddress(final String address) {
        this.address = address;
    }


    public void setEmail(final String email) {
        this.email = email;
    }


    public void setPhone(final String phone) {
        this.phone = phone;
    }


    public void setCustomer_number(final int customer_number) {
        this.customer_number = customer_number;
    }

}
