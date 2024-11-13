package vn.edu.hcmuaf.fit.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {
    private Integer id;
    private String name;
    private ProviderDTO provider;
    private double originalPrice;
    private double newPrice;
    private int quantity;
    private String color;
    private String screen;
    private String operatingSystem;
    private String camera;
    private String cpu;
    private String ram;
    private String storage;
    private String battery;
    private String description;
    private byte status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<ImageDTO> images;

    public ProductDTO() {
    }

    public ProductDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public double getOriginalPrice() {
        return originalPrice != 0 ? originalPrice : 0;
    }

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public double getNewPrice() {
        return newPrice != 0 ? newPrice : 0;
    }

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public int getQuantity() {
        return quantity != 0 ? quantity : 0;
    }

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public byte getStatus() {
        return status != 0 ? status : 0;
    }
}
