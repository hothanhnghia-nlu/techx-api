package vn.edu.hcmuaf.fit.api.service;

import vn.edu.hcmuaf.fit.api.dto.UserDTO;
import vn.edu.hcmuaf.fit.api.model.User;

import java.util.List;

public interface UserService {
    User saveUser(UserDTO userDTO);
    List<UserDTO> getUsers();
    User getUserByID(Integer id);
    User getUserInfo();
    User getUserByEmail(String email);
    User updateUserByID(Integer id, UserDTO userDTO);
    void deleteUserByID(Integer id);

}
