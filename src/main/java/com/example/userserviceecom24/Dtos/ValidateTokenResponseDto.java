package com.example.userserviceecom24.Dtos;

import com.example.userserviceecom24.Models.sessionStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateTokenResponseDto {
    private UserDto userDto;
    private sessionStatus sessionstatus;
}
