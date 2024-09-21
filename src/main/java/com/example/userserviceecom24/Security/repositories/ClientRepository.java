package com.example.userserviceecom24.Security.repositories;

import com.example.userserviceecom24.Security.models.Client;
import org.springframework.stereotype.Repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findByClientId(String clientId);
}