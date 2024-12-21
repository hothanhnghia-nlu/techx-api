package vn.edu.hcmuaf.fit.api.model;

import lombok.Data;

@Data
public class Payment {
    private Long id;
    private String currency;
    private Long amount;
    private String status;
    private String paymentIntentId;
}
