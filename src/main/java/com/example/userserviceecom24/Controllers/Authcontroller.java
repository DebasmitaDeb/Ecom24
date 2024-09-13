package com.example.userserviceecom24.Controllers;

import com.example.userserviceecom24.Dtos.*;
import com.example.userserviceecom24.Exceptions.UserDoesNotExistsException;
import com.example.userserviceecom24.Exceptions.UserExistsException;
import com.example.userserviceecom24.Models.sessionStatus;
import com.example.userserviceecom24.Services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class Authcontroller {

    private AuthService authService;

    public Authcontroller (AuthService authService){
        this.authService = authService;
    }


    @PostMapping("/login")
    public ResponseEntity<UserDto> logIn(@RequestBody LoginRequestDto request) throws UserDoesNotExistsException {

        return authService.logIn(request.getEmail(), request.getPassword());
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp( @RequestBody SignUpRequestDto request) throws UserExistsException {
        UserDto userDto = authService.signUp(request.getEmail(), request.getPassword());
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping("/validate")
    public ResponseEntity<ValidateTokenResponseDto> validateToken(@RequestBody ValidateTokenRequestDto request) {
        Optional<UserDto> userDto  = authService.validate(request.getToken(),request.getUserId());
        if (userDto.isEmpty()) {
            ValidateTokenResponseDto response = new ValidateTokenResponseDto();
            response.setSessionstatus(sessionStatus.INVALID);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        ValidateTokenResponseDto response = new ValidateTokenResponseDto();
        response.setSessionstatus(sessionStatus.ACTIVE);
        response.setUserDto(userDto.get());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
