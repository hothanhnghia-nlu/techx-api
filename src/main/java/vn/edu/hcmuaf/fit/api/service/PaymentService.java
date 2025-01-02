package vn.edu.hcmuaf.fit.api.service;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import vn.edu.hcmuaf.fit.api.dto.payment.reponse.PaymentResponseDTO;
import vn.edu.hcmuaf.fit.api.dto.payment.request.PaymentConfirmRequest;
import vn.edu.hcmuaf.fit.api.dto.payment.request.PaymentIntentRequest;

public interface PaymentService {
    PaymentIntent createCardPayment(PaymentIntentRequest request) throws StripeException;
    PaymentResponseDTO confirmPayment(PaymentConfirmRequest request) throws StripeException;
    PaymentResponseDTO cancelPayment(String paymentIntentId) throws StripeException;
}
