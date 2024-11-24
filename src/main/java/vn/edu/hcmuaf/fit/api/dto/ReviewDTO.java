package vn.edu.hcmuaf.fit.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReviewDTO {
    private Integer id;
    private UserDTO user;
    private ProductDTO product;
    private double rating;
    private String comment;
    private byte status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
