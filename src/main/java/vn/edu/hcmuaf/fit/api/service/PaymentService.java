package vn.edu.hcmuaf.fit.api.service;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import vn.edu.hcmuaf.fit.api.dto.payment.request.PaymentRequest;

public interface PaymentService {
    PaymentIntent createCardPayment(PaymentRequest request) throws StripeException;
    PaymentIntent confirmPayment(String paymentIntentId) throws StripeException;
    PaymentIntent cancelPayment(String paymentIntentId) throws StripeException;
}
