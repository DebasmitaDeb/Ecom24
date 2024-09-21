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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMapAdapter;

import java.util.HashMap;
import java.util.Optional;

@Getter
@Setter
@Service
public class AuthService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private SessionRepository sessionRepository;

    public AuthService(UserRepository userRepository, SessionRepository sessionRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.sessionRepository = sessionRepository;
    }

    public UserDto signUp(String email, String password) throws UserExistsException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (!optionalUser.isEmpty()) {
            throw new UserExistsException("User with " + email + " already exists.");
        }

        User user = new User();
        user.setEmail(email);
        user.setPasword(passwordEncoder.encode(password));

        User savedUser = userRepository.save(user);

        return UserDto.from(savedUser);
    }

    public ResponseEntity<UserDto> logIn(String email, String password) throws UserDoesNotExistsException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new UserDoesNotExistsException("User with " + email + " does not exists");
        }
        ;
        User user = optionalUser.get();
        if (!passwordEncoder.matches(password, user.getPasword())) {
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

    public Optional<UserDto> validate(String token, Long userId) {
        Optional<Session> optionalSession = sessionRepository.findByTokenAndUser_Id(token, userId);
        if (optionalSession.isEmpty()) {
            return Optional.empty();
        }
        Session session = optionalSession.get();
        if(!session.getSessionStatus().equals(sessionStatus.ACTIVE)){
            return Optional.empty();
        }
        User user = userRepository.findById(userId);
        UserDto userDto = UserDto.from(user);
        return Optional.of(userDto);
    }
}