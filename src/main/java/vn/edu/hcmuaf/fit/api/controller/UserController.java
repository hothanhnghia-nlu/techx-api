package vn.edu.hcmuaf.fit.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.api.dto.UserDTO;
import vn.edu.hcmuaf.fit.api.model.User;
import vn.edu.hcmuaf.fit.api.service.UserService;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@Tag(name = "User Controller")
public class UserController {
    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Create a new User
    @Operation(summary = "Create a new user", description = "Create a new user with form data")
    @PostMapping(consumes = {"multipart/form-data", "application/x-www-form-urlencoded"})
    public ResponseEntity<User> createUser(@ModelAttribute UserDTO user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    // Get all User
    @GetMapping
    @Operation(summary = "Get all User", description = ".")
    public List<UserDTO> getAllUsers() {
        return userService.getUsers();
    }

    // Get User by id
    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
        return new ResponseEntity<>(userService.getUserByID(id), HttpStatus.OK);
    }

    // Get User by id
    @GetMapping("/user-info")
    public ResponseEntity<User> getUserInformation() {
        return new ResponseEntity<>(userService.getUserInfo(), HttpStatus.OK);
    }

    // Update User by id
    @PutMapping(value = "{id}", consumes = {"multipart/form-data", "application/x-www-form-urlencoded"})
    public ResponseEntity<User> updateUserById(@PathVariable("id") int id,
                                               @ModelAttribute UserDTO userDTO) {
        return new ResponseEntity<>(userService.updateUserByID(id, userDTO), HttpStatus.OK);
    }

    // Delete User by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") int id) {
        userService.deleteUserByID(id);
        return new ResponseEntity<>("User " + id + " is deleted successfully!", HttpStatus.OK);
    }

}
