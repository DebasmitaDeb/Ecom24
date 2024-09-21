package com.example.userserviceecom24.Security.services;

import com.example.userserviceecom24.Models.User;
import com.example.userserviceecom24.Repository.UserRepository;
import com.example.userserviceecom24.Security.models.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private UserRepository userRepository;
    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);

        if(user.isEmpty()){throw new UsernameNotFoundException(username+" User not found");}
        return new CustomUserDetails(user.get());
    }
}
