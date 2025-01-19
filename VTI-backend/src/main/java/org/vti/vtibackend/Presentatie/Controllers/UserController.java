package org.vti.vtibackend.Presentatie.Controllers;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import org.vti.vtibackend.BLL.JwtTokenProvider;
import org.vti.vtibackend.BLL.Service.UserService;
import org.vti.vtibackend.model.User.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping
    public List<UserInfo> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("user/register")
    public ResponseEntity<UserDTO> createUser(@RequestBody CreateUserDTO users) {
        UserDTO userDTO = userService.createUser(users);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @GetMapping("/admin/user/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        UserDTO userDTO = userService.findByUsername(username);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("user/login")
    public ResponseEntity<?> authenticate(@RequestBody CreateUserDTO authRequest) {
        try {
            UserDTO userDTO = userService.authenticate(authRequest.getUsername(), authRequest.getPassword());
            String token = jwtTokenProvider.generateToken(userDTO.getUsername(), userDTO.getRole());
            return ResponseEntity.ok(new AuthUserResponse(token, userDTO));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Ongeldige inloggegevens");
        }
    }

    @GetMapping("admin/user/info")
    public ResponseEntity<List<UserInfo>> getUserInfo() {
        List<UserInfo> userInfo = userService.getUserInfo();
        return ResponseEntity.ok(userInfo);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserInfo> updateUser(
            @PathVariable int userId,
            @RequestBody UpdatedUser user) {
        try {
            UserInfo userInfo = new UserInfo(userId, user.getUsername(), user.getRole());
            UserInfo updatedUser = userService.updateUser(userId, userInfo);
            return ResponseEntity.ok(updatedUser);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Gebruiker niet gevonden
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Andere fout
        }
    }
}