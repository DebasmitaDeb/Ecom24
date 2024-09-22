package com.example.paymentservice.PaymentGateways;

public interface PaymentGateway {
    String generatePaymentLink(Long amount);
}
