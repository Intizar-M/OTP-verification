package com.example.controller;

import com.example.dto.LoginDto;
import com.example.dto.RegisterDto;
import com.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        try {
            String registrationResult = userService.register(registerDto);
            return new ResponseEntity<>(registrationResult, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to register user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    curl --location 'http://localhost:8080/register' \
//            --header 'Content-Type: application/json' \
//            --data-raw '{
//            "name": "Intizar",
//            "email": "innamillevien@gmail.com",
//            "password": "qwerty"
//            }'

    @PutMapping("/verify-account")
    public ResponseEntity<String> verifyAccount(@RequestParam String email,
                                                @RequestParam String otp) {
        return new ResponseEntity<>(userService.verifyAccount(email, otp), HttpStatus.OK);
    }

//    curl --location --request PUT 'http://localhost:8080/verify-account?email=innamillevien%40gmail.com&otp=212983'

    @PutMapping("/regenerate-otp")
    public ResponseEntity<String> regenerateOtp(@RequestParam String email) {
        return new ResponseEntity<>(userService.regenerateOtp(email), HttpStatus.OK);
    }

//    curl --location --request PUT 'http://localhost:8080/regenerate-otp' \
//            --header 'Content-Type: application/x-www-form-urlencoded' \
//            --data-urlencode 'email=innamillevien@gmail.com'

    @PutMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        return new ResponseEntity<>(userService.login(loginDto), HttpStatus.OK);
    }

//    curl --location --request PUT 'http://localhost:8080/login' \
//            --header 'Content-Type: application/json' \
//            --data-raw '{
//            "email": "innamillevien@gmail.com",
//            "password": "qwerty"
//            }'
}