package com.example.userserviceecom24.Repository;

import com.example.userserviceecom24.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    User save(User user);
}
