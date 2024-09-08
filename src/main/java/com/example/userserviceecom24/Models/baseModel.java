package com.example.userserviceecom24.Models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class baseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
}
