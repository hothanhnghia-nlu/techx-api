package vn.edu.hcmuaf.fit.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FavoriteDTO {
    private Integer id;
    private UserDTO user;
    private ProductDTO product;
    private byte status;
    private LocalDateTime createdAt;
}
