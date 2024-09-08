package com.example.userserviceecom24.Controllers;

import com.example.userserviceecom24.Services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public void getUserDetails(){

    }

    @PostMapping("/{id}/roles")
    public void setUserRoles(){

    }
}