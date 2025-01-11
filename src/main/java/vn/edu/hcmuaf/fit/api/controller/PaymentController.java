package vn.edu.hcmuaf.fit.api.controller;

import com.stripe.exception.CardException;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.api.dto.payment.request.PaymentConfirmRequest;
import vn.edu.hcmuaf.fit.api.dto.payment.request.PaymentIntentRequest;
import vn.edu.hcmuaf.fit.api.dto.payment.reponse.PaymentResponseDTO;
import vn.edu.hcmuaf.fit.api.service.PaymentService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Payment", description = "Payment management APIs")
public class PaymentController {

    private final PaymentService paymentService;

    @Operation(
            summary = "Create a payment",
            description = "Creates a new payment intent using Stripe"
    )
    @PostMapping("/create-payment")
    public ResponseEntity<Map<String, String>> createPayment(@RequestBody PaymentIntentRequest request) {
        try {
            PaymentIntent paymentIntent = paymentService.createCardPayment(request);

            Map<String, String> response = new HashMap<>();
            response.put("clientSecret", paymentIntent.getClientSecret());
            response.put("paymentIntentId", paymentIntent.getId());
            response.put("status", paymentIntent.getStatus());

            return ResponseEntity.ok(response);

        } catch (CardException e) {
            log.error("Card error occurred: {}", e.getMessage());
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            error.put("declineCode", e.getDeclineCode());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

        } catch (StripeException e) {
            log.error("Stripe error occurred: {}", e.getMessage());
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @Operation(
            summary = "Confirm a payment",
            description = "Confirms a payment intent using the payment intent ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Payment confirmed successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PaymentResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
    @PostMapping("/confirm")
    public ResponseEntity confirmPayment(@RequestBody PaymentConfirmRequest request
    ) {
        try {
            PaymentResponseDTO response = paymentService.confirmPayment(request);
            return ResponseEntity.ok(response);
        } catch (StripeException e) {
            log.error("Error confirming payment: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(
            summary = "Cancel a payment",
            description = "Cancels a payment intent using the payment intent ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Payment canceled successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PaymentResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
    @PostMapping("/cancel/{paymentIntentId}")
    public ResponseEntity<PaymentResponseDTO> cancelPayment(
            @Parameter(description = "Payment Intent ID", required = true)
            @PathVariable String paymentIntentId
    ) {
        try {
            PaymentResponseDTO response =  paymentService.cancelPayment(paymentIntentId);
            return ResponseEntity.ok(response);
        } catch (StripeException e) {
            log.error("Error canceling payment: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}