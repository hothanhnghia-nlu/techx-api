package vn.edu.hcmuaf.fit.api.service;

import vn.edu.hcmuaf.fit.api.dto.UserDTO;
import vn.edu.hcmuaf.fit.api.model.User;

import java.util.List;

public interface UserService {
    User saveUser(UserDTO userDTO);
    List<UserDTO> getUsers();
    List<UserDTO> getUserByRoleUser();
    User getUserByID(Integer id);
    User getUserInfo();
    User getUserByEmail(String email);
    User updateUser(UserDTO userDTO);
    void updatePassword(UserDTO userDTO);
    void updatePasswordByEmail(String email, UserDTO userDTO);
    void deleteUserByID(Integer id);
    void deleteAcc();
    void sendOtpToEmail(String email);
    boolean verifyOtp(String email, String otp);
    void updatePasswordByEmail(String email, String newPassword);
}
