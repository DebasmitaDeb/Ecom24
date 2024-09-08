package com.example.userserviceecom24.Models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Role extends baseModel {
    private String role;
}
