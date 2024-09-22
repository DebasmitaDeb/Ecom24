package com.example.paymentservice.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetPaymentLinkRequestDto {
    private Long orderId;
}
