package vn.edu.hcmuaf.fit.api.dto.auth;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignInRequest {
    @NotBlank
    @Email
    private String email;

    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

}