package org.vti.vtibackend.BLL.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.vti.vtibackend.BLL.Interface.ICustomerDAL;
import org.vti.vtibackend.BLL.Interface.IUserDAL;
import org.vti.vtibackend.DAL.Entity.Customer;
import org.vti.vtibackend.model.Customer.CustomerDTO;
import org.vti.vtibackend.model.User.CreateUserDTO;
import org.vti.vtibackend.model.User.UserDTO;
import org.vti.vtibackend.model.User.UserInfo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserService {

    private final IUserDAL userDAL;
    private final ICustomerDAL customerDAL;

    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(IUserDAL userDAL, ICustomerDAL customerDAL ,PasswordEncoder passwordEncoder) {
        this.userDAL = userDAL;
        this.customerDAL = customerDAL;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserInfo> getAllUsers() {
        return userDAL.findAll();
    }

    public UserDTO createUser(CreateUserDTO users) {
        if (users == null) {
            throw new NullPointerException("User cannot be null");
        }
        UserDTO existingUser = userDAL.findByUsername(users.getUsername());
        if (existingUser == null) {
            return userDAL.save(makeUser(users));
        } else {
            throw new BadCredentialsException("Username already exists");
        }
    }

    private UserDTO makeUser(CreateUserDTO users) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(users.getUsername());
        userDTO.setPassword(passwordEncoder.encode(users.getPassword()));
        userDTO.setRole(users.getRole());
        if ("CUSTOMER".equalsIgnoreCase(users.getRole())) {
            List<CustomerDTO> customers = customerDAL.findAll();
            if (customers.isEmpty()) {
                throw new IllegalArgumentException("Geen klanten gevonden. Kan geen klant-ID instellen.");
            }
            userDTO.setCustomer_id(customers.get(0).getCustomer_id());
        }
        System.out.println("Ingevoegde Customer ID: " + userDTO.getCustomer_id());

        return userDTO;
    }

    public UserDTO findByUsername(String username) {
        return userDAL.findByUsername(username);
    }

    public UserDTO authenticate(String username, String password) {
        UserDTO userDTO = userDAL.authenticateUser(username, passwordEncoder.encode(password));
        if (!passwordEncoder.matches(password, userDTO.getPassword())) {
            throw new BadCredentialsException("Ongeldige inloggegevens");
        }
        return new UserDTO(userDTO.getUser_id(), userDTO.getUsername(), userDTO.getRole(), userDTO.getCustomer_id());
    }

    public List<UserInfo> getUserInfo() {
        List<UserDTO> users = userDAL.getUsernamesAndRoles();

        // Converteer elke UserDTO naar een UserInfo
        return users.stream()
                .map(user -> new UserInfo(user.getUser_id(), user.getUsername(), user.getRole()))
                .collect(Collectors.toList());
    }

    public UserInfo updateUser(int user_id, UserInfo user) {
        UserDTO updatedUser = userDAL.UpdateUser(user_id, user);

        return new UserInfo(updatedUser.getUser_id(), updatedUser.getUsername(), updatedUser.getRole());
    }


}