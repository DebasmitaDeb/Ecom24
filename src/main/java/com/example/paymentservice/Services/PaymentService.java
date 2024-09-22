package com.example.paymentservice.Services;

import com.example.paymentservice.PaymentGateways.Stripe.StripePaymentGateway;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private StripePaymentGateway stripePaymentGateway;

    public PaymentService(StripePaymentGateway stripePaymentGateway) {
        this.stripePaymentGateway = stripePaymentGateway;
    }

    public String CreatePaymentLink(Long orderId){
        return stripePaymentGateway.generatePaymentLink(10000L);
    }
}
