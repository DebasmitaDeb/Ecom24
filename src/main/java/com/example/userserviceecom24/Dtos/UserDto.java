package com.example.userserviceecom24.Dtos;

import com.example.userserviceecom24.Models.Role;
import com.example.userserviceecom24.Models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserDto {
    private String email;
    private Set<Role> Roles = new HashSet<>();
    public static UserDto from (User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setRoles(user.getRoles());
        return userDto;
    }
}
