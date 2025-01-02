package vn.edu.hcmuaf.fit.api.mapper;

import com.stripe.model.PaymentIntent;
import org.springframework.stereotype.Component;
import vn.edu.hcmuaf.fit.api.dto.payment.reponse.PaymentResponseDTO;

@Component
public class PaymentIntentMapper implements GenericMapper<PaymentIntent, PaymentResponseDTO> {

    @Override
    public PaymentResponseDTO map(PaymentIntent paymentIntent) {
        return PaymentResponseDTO.builder()
                .paymentIntentId(paymentIntent.getId())
                .status(paymentIntent.getStatus())
                .clientSecret(paymentIntent.getClientSecret())
                .amount(paymentIntent.getAmount() / 100.0)
                .currency(paymentIntent.getCurrency())
                .build();
    }
}
