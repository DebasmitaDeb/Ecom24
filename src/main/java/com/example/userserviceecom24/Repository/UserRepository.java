package com.example.userserviceecom24.Repository;

import com.example.userserviceecom24.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    User save(User user);

    User findById(Long userId);
}
