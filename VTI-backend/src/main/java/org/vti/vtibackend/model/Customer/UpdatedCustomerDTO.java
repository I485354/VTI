package org.vti.vtibackend.model.Customer;





public class UpdatedCustomerDTO {
    private String name;
    private String company;
    private String email;
    private String phone;
    private String address;

    public UpdatedCustomerDTO() {}
    public UpdatedCustomerDTO(String name, String company, String email, String phone, String address) {
        this.name = name;
        this.company = company;
        this.email = email;
        this.phone = phone;
        this.address = address;

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
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }


}
