package com.example.productserviceecom24.Client.AuthDtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateTokenResponseDto {
    private UserDto userDto;
    private sessionStatus sessionstatus;
}
