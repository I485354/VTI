package org.vti.vtibackend.model.User;

public class UserInfo {
    private long user_id;
    private String username;
    private String role;
    private int customer_id;

    public UserInfo() {
    }

    public UserInfo(long user_id, String username, String role) {
        this.user_id = user_id;
        this.username = username;
        this.role = role;
    }

    // Getters
    public long getUser_id() {
        return user_id;
    }

    // Setters
    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getCustomer_id() {
        return customer_id;
    }
    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

}
