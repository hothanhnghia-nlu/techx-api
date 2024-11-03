package vn.edu.hcmuaf.fit.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImageDTO {
    private Integer id;
    private String name;
    private String url;
    private ProviderDTO provider;
    private List<ProductDTO> products;
}
