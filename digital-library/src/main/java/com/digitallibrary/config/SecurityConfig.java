package com.digitallibrary.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for testing
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/users/**").hasRole("ADMIN") // Only ADMINs can create users
                        .requestMatchers(HttpMethod.PUT, "/users/**").hasRole("ADMIN") // Only ADMINs can update users
                        .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN") // Only ADMINs can delete users
                        .requestMatchers(HttpMethod.GET, "/users/**").authenticated() // All authenticated users can view users
                        .anyRequest().permitAll() // Allow all other requests
                )
                .httpBasic(httpBasic -> httpBasic.realmName("MyApp")); // Configuring Basic Authentication explicitly

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
