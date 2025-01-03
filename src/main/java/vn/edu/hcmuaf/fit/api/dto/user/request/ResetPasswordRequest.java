package vn.edu.hcmuaf.fit.api.dto.user.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NonNull;

@Data
public class ResetPasswordRequest {
    @NotEmpty
    private String email;
    @NotEmpty
    private String newPassword;
}
