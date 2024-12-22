package vn.edu.hcmuaf.fit.api.dto.payment.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Payment Intent object")
public class PaymentIntentRequest {
    @Schema(description = "Amount to charge", example = "100.50", required = true)
    private long amount;
}