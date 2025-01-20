package org.vti.vtibackend.model.User;

public class CreateUserDTO {
    private String username;
    private String password;
    private int customer_id;

    public CreateUserDTO(String username, String password, int customer_id) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
