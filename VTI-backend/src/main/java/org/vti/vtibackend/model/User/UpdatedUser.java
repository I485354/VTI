package org.vti.vtibackend.model.User;

public class UpdatedUser {
    String username;
    String role;
    int customer_id;

    public UpdatedUser(String username, String role, int customer_id) {
        this.username = username;
        this.role = role;
        this.customer_id = customer_id;
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
