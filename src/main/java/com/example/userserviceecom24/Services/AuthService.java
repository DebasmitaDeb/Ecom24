package com.example.userserviceecom24.Services;

import com.example.userserviceecom24.Dtos.UserDto;
import com.example.userserviceecom24.Exceptions.PasswordDoesNotMatchException;
import com.example.userserviceecom24.Exceptions.UserDoesNotExistsException;
import com.example.userserviceecom24.Exceptions.UserExistsException;
import com.example.userserviceecom24.Models.User;
import com.example.userserviceecom24.Repository.SessionRepository;
import com.example.userserviceecom24.Repository.UserRepository;
import com.example.userserviceecom24.Models.sessionStatus;
import com.example.userserviceecom24.Models.Session;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMapAdapter;

import java.util.HashMap;
import java.util.Optional;

@Getter
@Setter
@Service
public class AuthService {

    private static UserRepository userRepository;
    private static BCryptPasswordEncoder bCryptPasswordEncoder;
    private static SessionRepository sessionRepository;

    public AuthService(UserRepository userRepository, SessionRepository sessionRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.sessionRepository = sessionRepository;
    }

    public static UserDto signUp(String email, String password) throws UserExistsException {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByEmail(email));
        if (!optionalUser.isEmpty()) {
            throw new UserExistsException("User with " + email + " already exists.");
        }

        User user = new User();
        user.setEmail(email);
        user.setPasword(bCryptPasswordEncoder.encode(password));

        User savedUser = userRepository.save(user);

        return UserDto.from(savedUser);
    }

    public static ResponseEntity<UserDto> logIn(String email, String password) throws UserDoesNotExistsException {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByEmail(email));
        if (optionalUser.isEmpty()) {
            throw new UserDoesNotExistsException("User with " + email + " does not exists");
        }
        ;
        User user = optionalUser.get();
        if (!bCryptPasswordEncoder.matches(password, user.getPasword())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ;

        String token = RandomStringUtils.randomAscii(20);
        MultiValueMapAdapter<String, String> header = new MultiValueMapAdapter<>(new HashMap<>());
        header.add("Auth_Token", token);

        Session session = new Session();
        session.setSessionStatus(sessionStatus.ACTIVE);
        session.setUser(user);
        session.setToken(token);
        sessionRepository.save(session);

        UserDto userDto = UserDto.from(user);
        ResponseEntity<UserDto> response = new ResponseEntity<>(
                userDto,
                header,
                HttpStatus.OK
        );
        return response;
    }
}