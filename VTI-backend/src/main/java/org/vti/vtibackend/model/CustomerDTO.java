package org.vti.vtibackend.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    private int customer_id;
    private String name;
    private String company;
    private String address;
    private String email;
    private String phone;

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public int getCustomer_id() {
        return this.customer_id;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public String getName() {
        return this.name;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public String getCompany() {
        return this.company;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public String getAddress() {
        return this.address;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public String getEmail() {
        return this.email;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public String getPhone() {
        return this.phone;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public void setCustomer_id(final int customer_id) {
        this.customer_id = customer_id;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public void setName(final String name) {
        this.name = name;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public void setCompany(final String company) {
        this.company = company;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public void setAddress(final String address) {
        this.address = address;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public void setEmail(final String email) {
        this.email = email;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public void setPhone(final String phone) {
        this.phone = phone;
    }

}