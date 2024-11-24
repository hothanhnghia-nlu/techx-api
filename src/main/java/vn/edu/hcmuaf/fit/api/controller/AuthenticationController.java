package vn.edu.hcmuaf.fit.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.api.dto.auth.SignInRequest;
import vn.edu.hcmuaf.fit.api.exception.ApiRequestException;
import vn.edu.hcmuaf.fit.api.service.AuthenticationService;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "localhost:8080")
@RequestMapping(path = "/api/v1/auth")
@Tag(name = "Authentication Controller")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Operation(summary = "Sign In", description = "Authenticate and sign in a user.")
    @PostMapping(path = "/sign-in")
    public ResponseEntity signIn(@RequestBody @Valid SignInRequest request) {
        log.info("Received login request for user: {}", request.getEmail());
        try {
            var res = authenticationService.authenticate(request);
            log.info("Login successful for user: {}", request.getEmail());
            return new ResponseEntity(res, HttpStatus.OK);
        } catch (ApiRequestException e) {
            log.error("Login failed", e);
            throw e;
        } catch (Exception e) {
            log.error("An error occurred during login for user: {}", request.getEmail(), e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Logout", description = "Log out the currently authenticated user.")
    @PostMapping(path = "/sign-out")
    public ResponseEntity logout() {
        try {
            log.info("Received logout request");
            authenticationService.logout();
            log.info("Logout successful");
            return new ResponseEntity(HttpStatus.OK);

        } catch (ApiRequestException e) {
            throw e;
        } catch (Exception e) {
            log.error("An error occurred during logout: {}", e.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
