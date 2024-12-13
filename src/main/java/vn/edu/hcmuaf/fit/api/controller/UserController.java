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

    // Get all User by user role
    @GetMapping("/by-role")
    @Operation(summary = "Get all User by user role", description = ".")
    public List<UserDTO> getUsersByUserRole() {
        return userService.getUserByRoleUser();
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

    // Update User authed
    @PutMapping(value = "/update-user",
                consumes = {"multipart/form-data", "application/x-www-form-urlencoded"})
    public ResponseEntity<User> updateUserAuthed(@ModelAttribute UserDTO userDTO) {
        return new ResponseEntity<>(userService.updateUser(userDTO), HttpStatus.OK);
    }

    // Changed password
    @PutMapping(value = "/change-password",
                consumes = {"multipart/form-data", "application/x-www-form-urlencoded"})
    public ResponseEntity<String> changePassword(@ModelAttribute UserDTO userDTO) {
        userService.updatePassword(userDTO);
        return new ResponseEntity<>("User is changed password successfully!", HttpStatus.OK);
    }

    // Changed password by email
    @PutMapping(value = "/change-password/by-email",
                consumes = {"multipart/form-data", "application/x-www-form-urlencoded"})
    public ResponseEntity<String> changePassword(@RequestParam String email,
                                                 @ModelAttribute UserDTO userDTO) {
        userService.updatePasswordByEmail(email, userDTO);
        return new ResponseEntity<>("User is changed password successfully!", HttpStatus.OK);
    }

    // Delete User by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") int id) {
        userService.deleteUserByID(id);
        return new ResponseEntity<>("User " + id + " is deleted successfully!", HttpStatus.OK);
    }

    // Delete User account
    @DeleteMapping("/delete-account")
    public ResponseEntity<String> deleteAccount() {
        userService.deleteAcc();
        return new ResponseEntity<>("Account is deleted successfully!", HttpStatus.OK);
    }

}
