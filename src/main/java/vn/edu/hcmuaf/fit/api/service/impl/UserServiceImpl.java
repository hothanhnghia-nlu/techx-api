package vn.edu.hcmuaf.fit.api.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.api.dto.UserDTO;
import vn.edu.hcmuaf.fit.api.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.api.model.User;
import vn.edu.hcmuaf.fit.api.repository.UserRepository;
import vn.edu.hcmuaf.fit.api.service.AuthenticationService;
import vn.edu.hcmuaf.fit.api.service.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public User saveUser(UserDTO userDTO) {
        User user = new User();
        user.setFullName(userDTO.getFullName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(0);
        user.setStatus((byte) 1);
        user.setCreatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    @Override
    public List<UserDTO> getUsers() {
        List<User> users = userRepository.findAll();

        return users.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getUserByRoleUser() {
        List<User> users = userRepository.findAll().stream()
                .filter(u -> u.getRole() != 2)
                .toList();

        return users.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFullName(user.getFullName());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        userDTO.setStatus(user.getStatus());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setUpdatedAt(user.getUpdatedAt());

        return userDTO;
    }

    @Override
    public User getUserByID(Integer id) {
        return userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", id));
    }

    @Override
    public User getUserInfo() {
        int userId = authenticationService.getCurrentUserId();
        return userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }

    @Override
    public User updateUser(UserDTO userDTO) {
        int userId = authenticationService.getCurrentUserId();
        User existingUser = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", userId));

        existingUser.setFullName(userDTO.getFullName() != null ? userDTO.getFullName() : existingUser.getFullName());
        existingUser.setPhoneNumber(userDTO.getPhoneNumber() != null ? userDTO.getPhoneNumber() : existingUser.getPhoneNumber());
        existingUser.setEmail(userDTO.getEmail() != null ? userDTO.getEmail() : existingUser.getEmail());
        existingUser.setRole(userDTO.getRole() != 2 ? userDTO.getRole() : existingUser.getRole());
        existingUser.setStatus(userDTO.getStatus() != 0 ? userDTO.getStatus() : existingUser.getStatus());
        existingUser.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(existingUser);
    }

    @Override
    public void updatePassword(UserDTO userDTO) {
        int userId = authenticationService.getCurrentUserId();
        User existingUser = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", userId));

        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());

        existingUser.setPassword(userDTO.getPassword() != null ? encodedPassword : existingUser.getPassword());
        existingUser.setUpdatedAt(LocalDateTime.now());

        userRepository.save(existingUser);
    }

    @Override
    public void updatePasswordByEmail(String email, UserDTO userDTO) {
        User existingUser = userRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("User", "email", email));

        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());

        existingUser.setPassword(userDTO.getPassword() != null ? encodedPassword : existingUser.getPassword());
        existingUser.setUpdatedAt(LocalDateTime.now());

        userRepository.save(existingUser);
    }

    @Override
    public void deleteUserByID(Integer id) {
        userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", id));

        userRepository.deleteById(id);
    }

    @Override
    public void deleteAcc() {
        int userId = authenticationService.getCurrentUserId();
        userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", userId));

        userRepository.deleteById(userId);
    }
}
