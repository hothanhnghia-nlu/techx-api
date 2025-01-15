package vn.edu.hcmuaf.fit.api.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.api.dto.UserDTO;
import vn.edu.hcmuaf.fit.api.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.api.model.User;
import vn.edu.hcmuaf.fit.api.repository.UserRepository;
import vn.edu.hcmuaf.fit.api.service.AuthenticationService;
import vn.edu.hcmuaf.fit.api.service.UserService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
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
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private RedisTemplate<String, String> redisTemplate; // Redis để lưu OTP tạm thời

    private static final int OTP_EXPIRATION_MINUTES = 2; // Thời gian sống của OTP (5 phút)

    @Override
    public User saveUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new IllegalArgumentException("Email đã tồn tại!");
        }

        User user = new User();
        user.setFullName(userDTO.getFullName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(1); // Default ROLE USER
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

    @Override
    public void sendOtpToEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User không tồn tại"));
        //tao ma otp
        String otp = String.format("%06d", new Random().nextInt(1000000));
        // luu otp vao redis voi thoi gian het han la 2 phut
        redisTemplate.opsForValue().set("OTP_" + email, otp, Duration.ofMinutes(OTP_EXPIRATION_MINUTES));
        log.info("OTP {} được lưu vào Redis với key {}", otp, "OTP_" + email);
        // gui email chua otp
        SimpleMailMessage emailMessage = new SimpleMailMessage();
        emailMessage.setTo(email);
        emailMessage.setSubject("Mã OTP đặt lại mật khẩu");
        emailMessage.setText("Mã OTP của bạn là: " + otp + ". Mã này sẽ hết hạn sau " + OTP_EXPIRATION_MINUTES + " phút.");
        mailSender.send(emailMessage);

    }

    @Override
    public boolean verifyOtp(String email, String otp) {
        String storedOtp = redisTemplate.opsForValue().get("OTP_" + email);
        log.info("OTP từ Redis: {}", storedOtp);
        if (storedOtp == null) {
            log.warn("OTP không tồn tại hoặc đã hết hạn cho email {}", email);
            return false;
        }
        return storedOtp.trim().equals(otp.trim());
    }

    @Override
    public void updatePasswordByEmail(String email, String newPassword) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
