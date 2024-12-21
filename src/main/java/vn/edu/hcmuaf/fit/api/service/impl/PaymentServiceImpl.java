package vn.edu.hcmuaf.fit.api.service.impl;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.api.dto.payment.request.PaymentRequest;
import vn.edu.hcmuaf.fit.api.service.PaymentService;

import java.util.HashMap;
import java.util.Map;


@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    @Override
    public PaymentIntent createCardPayment(PaymentRequest request) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        // Tạo PaymentMethod
        Map<String, Object> cardParams = new HashMap<>();
        cardParams.put("number", "4242424242424242");
        cardParams.put("exp_month", 12);
        cardParams.put("exp_year", 2024);
        cardParams.put("cvc", "123");

        Map<String, Object> paymentMethodParams = new HashMap<>();
        paymentMethodParams.put("type", "card");
        paymentMethodParams.put("card", cardParams);

        PaymentMethod paymentMethod = PaymentMethod.create(paymentMethodParams);

        // Tạo PaymentIntent
        PaymentIntentCreateParams intentParams = PaymentIntentCreateParams.builder()
                .setAmount((long) (request.getAmount() * 100))
                .setCurrency("usd")
                .setPaymentMethod(paymentMethod.getId())
                .setConfirmationMethod(PaymentIntentCreateParams.ConfirmationMethod.MANUAL)
                .setCaptureMethod(PaymentIntentCreateParams.CaptureMethod.AUTOMATIC)
                .setConfirm(true)
                .build();

        PaymentIntent paymentIntent = PaymentIntent.create(intentParams);
        log.info("Created payment intent: {}", paymentIntent.getId());
        return paymentIntent;
    }

    @Override
    public PaymentIntent confirmPayment(String paymentIntentId) throws StripeException {
        Stripe.apiKey = stripeSecretKey;
        PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);
        PaymentIntent confirmedIntent = paymentIntent.confirm();
        log.info("Confirmed payment intent: {}", paymentIntentId);
        return confirmedIntent;
    }

    @Override
    public PaymentIntent cancelPayment(String paymentIntentId) throws StripeException {
        Stripe.apiKey = stripeSecretKey;
        PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);
        PaymentIntent canceledIntent = paymentIntent.cancel();
        log.info("Canceled payment intent: {}", paymentIntentId);
        return canceledIntent;
    }
}