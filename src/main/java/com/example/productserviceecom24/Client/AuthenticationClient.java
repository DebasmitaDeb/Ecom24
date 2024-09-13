package com.example.productserviceecom24.Client;

import com.example.productserviceecom24.Client.AuthDtos.ValidateTokenRequestDto;
import com.example.productserviceecom24.Client.AuthDtos.ValidateTokenResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
@Component
public class AuthenticationClient {
    private RestTemplate restTemplate;

    public AuthenticationClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ValidateTokenResponseDto validate(String token, Long userId) {
        ValidateTokenRequestDto requestDto = new ValidateTokenRequestDto();
        requestDto.setToken(token);
        requestDto.setUserId(userId);

        ResponseEntity<ValidateTokenResponseDto> l = restTemplate.postForEntity(
                "http://localhost:8000/auth/validate",
                requestDto,
                ValidateTokenResponseDto.class);

        return l.getBody();
    }

}
