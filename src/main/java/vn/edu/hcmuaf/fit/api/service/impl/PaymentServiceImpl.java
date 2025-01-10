package vn.edu.hcmuaf.fit.api.service.impl;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentConfirmParams;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.api.dto.payment.reponse.PaymentResponseDTO;
import vn.edu.hcmuaf.fit.api.dto.payment.request.PaymentConfirmRequest;
import vn.edu.hcmuaf.fit.api.dto.payment.request.PaymentIntentRequest;
import vn.edu.hcmuaf.fit.api.mapper.PaymentIntentMapper;
import vn.edu.hcmuaf.fit.api.service.PaymentService;


@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentIntentMapper paymentIntentMapper;

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    @Override
    public PaymentIntent createCardPayment(PaymentIntentRequest request) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        // Giả sử bạn có một hàm để chuyển đổi từ VND sang USD
        double exchangeRate = getExchangeRateVndToUsd(); // Lấy tỷ giá hối đoái
        long amountInUsd = convertVndToUsd(request.getAmount(), exchangeRate);

        // Tạo PaymentIntent
        PaymentIntentCreateParams intentParams = PaymentIntentCreateParams.builder()
//                .setAmount(request.getAmount()) // Số tiền tính bằng đồng (VND)
                .setAmount(amountInUsd)
                .setCurrency("usd")
                .setConfirmationMethod(PaymentIntentCreateParams.ConfirmationMethod.MANUAL)
                .setCaptureMethod(PaymentIntentCreateParams.CaptureMethod.AUTOMATIC)
                .build();

        PaymentIntent paymentIntent = PaymentIntent.create(intentParams);
        log.info("Created payment intent: {}", paymentIntent.getId());
        return paymentIntent;
    }

    @Override
    public PaymentResponseDTO confirmPayment(PaymentConfirmRequest request) throws StripeException {
        Stripe.apiKey = stripeSecretKey;
        PaymentIntent paymentIntent = PaymentIntent.retrieve(request.getPaymentIntentId());
        // Gọi confirm() để xác nhận giao dịch
        PaymentIntentConfirmParams confirmParams = PaymentIntentConfirmParams.builder()
                .setPaymentMethod(request.getPaymentMethodId()) // Thêm PaymentMethod nếu cần
                .build();
        PaymentIntent confirmedIntent = paymentIntent.confirm(confirmParams);
        PaymentResponseDTO paymentResponseDTO = paymentIntentMapper.map(confirmedIntent);
        log.info("Confirmed payment intent: {}", confirmedIntent.getId());
        return paymentResponseDTO;
    }

    @Override
    public PaymentResponseDTO cancelPayment(String paymentIntentId) throws StripeException {
        Stripe.apiKey = stripeSecretKey;
        PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);
        PaymentIntent canceledIntent = paymentIntent.cancel();
        PaymentResponseDTO paymentResponseDTO = paymentIntentMapper.map(canceledIntent);
        log.info("Canceled payment intent: {}", paymentIntentId);
        return paymentResponseDTO;
    }

    // Hàm chuyển đổi từ VND sang USD
    private long convertVndToUsd(double amountVnd, double exchangeRate) {
        // Chuyển đổi từ VND sang USD và làm tròn xuống
        double amountUsd = amountVnd / exchangeRate;
        return Math.round(amountUsd * 100); // Nhân với 100 để chuyển sang cents
    }

    // Hàm lấy tỷ giá hối đoái từ VND sang USD (giả sử tỷ giá cố định)
    private double getExchangeRateVndToUsd() {
        // Ví dụ: tỷ giá cố định 1 USD = 24,000 VND
        return 24000.0;
    }
}