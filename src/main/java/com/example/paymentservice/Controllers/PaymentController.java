package com.example.paymentservice.Controllers;

import com.example.paymentservice.Dtos.GetPaymentLinkRequestDto;
import com.example.paymentservice.Services.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay")
public class PaymentController {
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/")
    public String GetPaymentLink(@RequestBody GetPaymentLinkRequestDto request){

        return paymentService.CreatePaymentLink(request.getOrderId());
    }
}
