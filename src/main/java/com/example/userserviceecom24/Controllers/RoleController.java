package com.example.userserviceecom24.Controllers;

import com.example.userserviceecom24.Services.RoleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;;
    }

    @PostMapping
    public void createRole(){

    }
}
