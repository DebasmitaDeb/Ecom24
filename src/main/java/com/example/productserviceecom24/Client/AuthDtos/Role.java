package com.example.productserviceecom24.Client.AuthDtos;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Role extends baseModel {
    private String role;
}
