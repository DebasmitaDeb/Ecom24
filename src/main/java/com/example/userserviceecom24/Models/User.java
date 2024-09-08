package com.example.userserviceecom24.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class User extends baseModel {
    private String email;
    private String pasword;
    @ManyToMany (fetch = FetchType.EAGER)
    private Set<Role> Roles = new HashSet<>();
}
