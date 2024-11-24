package vn.edu.hcmuaf.fit.api.dto.auth.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignInResponse {
    private String accessToken;
    private String refreshToken;
//    private Platform platform;
}
