package org.vti.vtibackend.model.User;




public class UserDTO {

    private long user_id;
    private String username;
    private String password;
    private String role;

    public UserDTO() {}
    public UserDTO(long user_id, String username, String password, String role) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.role = role;
    }


    public long getUser_id() {
        return this.user_id;
    }


    public String getUsername() {
        return this.username;
    }


    public String getPassword() {
        return this.password;
    }


    public String getRole() {
        return this.role;
    }


    public void setUser_id(final long user_id) {
        this.user_id = user_id;
    }


    public void setUsername(final String username) {
        this.username = username;
    }


    public void setPassword(final String password) {
        this.password = password;
    }


    public void setRole(final String role) {
        this.role = role;
    }

}