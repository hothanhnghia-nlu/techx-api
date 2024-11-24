package vn.edu.hcmuaf.fit.api.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.api.dto.auth.SignInRequest;
import vn.edu.hcmuaf.fit.api.dto.auth.request.ResetPasswordRequest;
import vn.edu.hcmuaf.fit.api.dto.auth.response.SignInResponse;
import vn.edu.hcmuaf.fit.api.enums.TokenType;
import vn.edu.hcmuaf.fit.api.exception.ApiRequestException;
import vn.edu.hcmuaf.fit.api.exception.UserNotFoundException;
import vn.edu.hcmuaf.fit.api.repository.UserRepository;
import vn.edu.hcmuaf.fit.api.security.jwt.JwtService;
import vn.edu.hcmuaf.fit.api.service.AuthenticationService;
import vn.edu.hcmuaf.fit.api.model.User;
import vn.edu.hcmuaf.fit.api.model.Token;


import java.util.Date;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final int NO_LOGIN_USER_ID = -1;


    private final UserDetailsService userDetailsService;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public SignInResponse authenticate(SignInRequest request) {
        try {
            var userDetails = userDetailsService.loadUserByUsername(request.getEmail()); // Check user exists
            log.info("user load từ db {}", userDetails);
            log.info("Đã tải thông tin người dùng cho email: {}", request.getEmail());

            var authentication = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
            authenticationManager.authenticate(authentication);// check enable user or user locked
            String accessToken = jwtService.generateToken(userDetails);
            // additional information v2
            String refreshToken = UUID.randomUUID().toString();
            return new SignInResponse(accessToken, refreshToken);
        } catch (UsernameNotFoundException e) {
            log.error("User not found", e.getMessage());
            throw new ApiRequestException(e.getMessage());
        } catch (DisabledException e) {
            log.error("User is disabled", e);
            throw new ApiRequestException(e.getMessage());
        } catch (Exception e) {
            log.error("Login failed", e);
            throw new ApiRequestException(e.getMessage());  // Generic user-friendly message
        }
    }

    @Override
    public void logout() {
        SecurityContextHolder.getContext().setAuthentication(null); // Clear authentication context
        SecurityContextHolder.clearContext(); // Optionally clear entire context
    }

    @Override
    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated();
    }

    @Override
    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                return ((UserDetails) principal).getUsername();
            }
        }
        return null;
    }

    private UserDetails userDetails() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .filter(UserDetails.class::isInstance)
                .map(UserDetails.class::cast)
                .orElse(null);
    }

    @Override
    public Long getCurrentUserId() {
        return null;
    }

    @Override
    public void verifyNewUser(String tokenValue) {

    }

    @Override
    public void initiatePasswordReset(String username) {

    }

    @Override
    public void resetPassword(ResetPasswordRequest resetPasswordRequest) {

    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with email " + email + " not found"));
    }

//    private Token generateResetToken(User user) {
//        return Token.builder()
//                .expiredAt(DateUtils.addDays(new Date(), 1))
//                .value(UUID.randomUUID().toString())
//                .type(TokenType.RESET_PASSWORD)
//                .userId(user.getId())
//                .build();
//    }

}