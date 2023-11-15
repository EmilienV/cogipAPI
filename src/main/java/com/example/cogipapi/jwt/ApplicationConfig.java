package com.example.cogipapi.jwt;


import com.example.cogipapi.authorisation.CustomUserDetailsService;
import com.example.cogipapi.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;


@Configuration
public class ApplicationConfig {

    private final UserRepository userRepository;

    public ApplicationConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean(name = "jwtUserDetailsService")
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService(userRepository);
    }
}
