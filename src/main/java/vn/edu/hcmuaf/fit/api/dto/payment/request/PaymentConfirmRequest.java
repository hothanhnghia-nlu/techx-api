package vn.edu.hcmuaf.fit.api.dto.payment.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Payment Confirmation Request")
public class PaymentConfirmRequest {
    @Schema(description = "Payment status", example = "SUCCESS")
    private String paymentStatus;
    @Schema(description = "Transaction ID", example = "trans_123456")
    private String transactionId;
    // thêm các trường cần thiết khác
    // getter, setter
}
