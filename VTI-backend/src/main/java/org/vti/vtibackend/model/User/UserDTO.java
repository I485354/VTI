package org.vti.vtibackend.model.User;


public class UserDTO {

    private long user_id;
    private String username;
    private String password;
    private String role;
    private Integer customer_id;

    public UserDTO() {
    }

    public UserDTO(long user_id, String username, String password, String role) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UserDTO(long user_id, String username, String role, Integer customer_id) {
        this.user_id = user_id;
        this.username = username;
        this.role = role;
        this.customer_id = customer_id;
    }
    public UserDTO(long user_id, String username, String role) {
        this.user_id = user_id;
        this.username = username;
        this.role = role;
    }



    //Getters
    public long getUser_id() {
        return this.user_id;
    }

    //Setters
    public void setUser_id(final long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(final String role) {
        this.role = role;
    }

    public Integer getCustomer_id() {
        return this.customer_id;
    }
    public void setCustomer_id(final Integer customer_id) {
        this.customer_id = customer_id;
    }


}