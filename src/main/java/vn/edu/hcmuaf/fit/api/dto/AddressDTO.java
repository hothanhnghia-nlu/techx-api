package vn.edu.hcmuaf.fit.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddressDTO {
    private Integer id;
    private UserDTO user;
    private String detail;
    private String ward;
    private String city;
    private String province;
    private String note;
    private byte status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
