package vn.edu.hcmuaf.fit.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.api.dto.UserDTO;
import vn.edu.hcmuaf.fit.api.dto.user.request.ResetPasswordRequest;
import vn.edu.hcmuaf.fit.api.model.User;
import vn.edu.hcmuaf.fit.api.service.UserService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/users")
@Tag(name = "User Controller")
public class UserController {
    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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

    @GetMapping("/fotgot-password/send-otp")
    public ResponseEntity<String> sendOTP(@RequestParam String email) {
        userService.sendOtpToEmail(email);
        return new ResponseEntity<>("OTP sent successfully!", HttpStatus.OK);
    }

    @GetMapping("/forgot-password/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestParam String email, @RequestParam String otp) {
        boolean isValid = userService.verifyOtp(email, otp);
        if (isValid) {
            return new ResponseEntity<>("Mã OTP hợp lệ!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Mã OTP không hợp lệ!", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/forgot-password/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
        String email = request.getEmail();
        String newPassword = request.getNewPassword();
        userService.updatePasswordByEmail(email, newPassword);
        return new ResponseEntity<>("Mật khẩu đã được cập nhật thành công!", HttpStatus.OK);
    }
}
