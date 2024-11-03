package vn.edu.hcmuaf.fit.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserDTO {
    private Integer id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String password;
    private int role;
    private byte status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
