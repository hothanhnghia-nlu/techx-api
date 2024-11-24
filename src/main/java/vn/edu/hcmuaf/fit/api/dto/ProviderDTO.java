package vn.edu.hcmuaf.fit.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProviderDTO {
    private Integer id;
    private String name;
    private ImageDTO image;
    private byte status;

    public ProviderDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProviderDTO() {
    }

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public byte getStatus() {
        return status != 0 ? status : 0;
    }
}
