package com.example.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "myadmin123"; // Replace with the plain password you're testing
        String encodedPassword = encoder.encode(rawPassword);

        System.out.println("Encoded password: " + encodedPassword);
        System.out.println("Matches: " + encoder.matches(rawPassword, "$2y$10$7zAAItkmn/VSUAibvkj42uI3uhh0I.hlA3qua8RLLpHgf4/KzaPnC")); // Replace with the hash in your database
    }
}
