package vn.edu.hcmuaf.fit.api.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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

    public UserDTO() {
    }

    public UserDTO(Integer id, String fullName, String phoneNumber, String email) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

}
