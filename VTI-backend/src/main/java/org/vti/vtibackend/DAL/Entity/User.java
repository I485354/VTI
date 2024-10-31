package org.vti.vtibackend.DAL.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;
    private String username;
    private String password;
    private String role;

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public long getUser_id() {
        return this.user_id;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public String getUsername() {
        return this.username;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public String getPassword() {
        return this.password;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public String getRole() {
        return this.role;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public void setUser_id(final long user_id) {
        this.user_id = user_id;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public void setUsername(final String username) {
        this.username = username;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public void setPassword(final String password) {
        this.password = password;
    }

    @java.lang.SuppressWarnings("all")
    @lombok.Generated
    public void setRole(final String role) {
        this.role = role;
    }
}
