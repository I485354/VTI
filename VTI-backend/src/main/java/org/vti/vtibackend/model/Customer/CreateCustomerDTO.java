package org.vti.vtibackend.model.Customer;



public class CreateCustomerDTO {
    private String name;
    private String company;
    private String address;
    private String email;
    private String phone;

    public CreateCustomerDTO() {}
    public CreateCustomerDTO(String name, String company, String address, String email, String phone) {
        this.name = name;
        this.company = company;
        this.address = address;
        this.email = email;
        this.phone = phone;

    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

}
