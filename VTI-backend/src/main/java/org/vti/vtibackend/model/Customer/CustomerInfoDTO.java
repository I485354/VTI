package org.vti.vtibackend.model.Customer;


public class CustomerInfoDTO {
    private String name;
    private String company;
    private String address;
    private String email;
    private String phone;
    private int customer_number;

    public CustomerInfoDTO(String name, String company, String address, String email, String phone, int customer_number) {
        this.name = name;
        this.company = company;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.customer_number = customer_number;
    }
    public CustomerInfoDTO() {
   }

   public String getName() {
        return name;
   }
   public String getCompany() {
        return company;
   }
   public String getAddress() {
        return address;
   }
   public String getEmail() {
        return email;
   }
   public String getPhone() {
        return phone;
   }
   public int getCustomer_number() {
        return customer_number;
   }

   public void setName(String name) {
        this.name = name;
   }
   public void setCompany(String company) {
        this.company = company;
   }
   public void setAddress(String address) {
        this.address = address;
   }
   public void setEmail(String email) {
        this.email = email;
   }
   public void setPhone(String phone) {
        this.phone = phone;
   }
   public void setCustomer_number(int customer_number) {
        this.customer_number = customer_number;
   }
}
