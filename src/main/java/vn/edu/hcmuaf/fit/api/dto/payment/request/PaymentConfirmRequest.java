package vn.edu.hcmuaf.fit.api.dto.payment.request;

import com.stripe.model.PaymentIntent;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Payment Confirm Request")
public class PaymentConfirmRequest {
    private String paymentIntentId;
    private String paymentMethodId;
}
