package vn.edu.hcmuaf.fit.api.dto.payment.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Payment request object")
public class PaymentRequest {
    @Schema(description = "Amount to charge", example = "100.50", required = true)
    private Double amount;

    @Schema(description = "Payment Method ID", example = "pm_card_visa", required = true)
    private String paymentMethodId;
}