package vn.edu.hcmuaf.fit.api.service;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import vn.edu.hcmuaf.fit.api.dto.payment.request.PaymentConfirmRequest;
import vn.edu.hcmuaf.fit.api.dto.payment.request.PaymentIntentRequest;

public interface PaymentService {
    PaymentIntent createCardPayment(PaymentIntentRequest request) throws StripeException;
    PaymentIntent confirmPayment(PaymentConfirmRequest request) throws StripeException;
    PaymentIntent cancelPayment(String paymentIntentId) throws StripeException;
}
