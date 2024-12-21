package vn.edu.hcmuaf.fit.api.dto.payment.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Payment Response DTO")
public class PaymentResponseDTO {
    @Schema(description = "Payment intent ID", example = "pi_1234567890")
    private String paymentIntentId;

    @Schema(description = "Payment status", example = "succeeded")
    private String status;

    @Schema(description = "Client secret", example = "pi_1234567890_secret_1234567890")
    private String clientSecret;

    @Schema(description = "Amount", example = "100.50")
    private Double amount;

    @Schema(description = "Currency", example = "usd")
    private String currency;
}
