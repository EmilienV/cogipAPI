package com.example.cogipapi.authorisation;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new YourUserDetailsService(); // Replace with your actual UserDetailsService implementation
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .antMatchers("/api/admin/**").hasRole("ADMIN")
                                .antMatchers("/api/accountant/**").hasAnyRole("ADMIN", "ACCOUNTANT")
                                .antMatchers("/api/intern/**").hasAnyRole("ADMIN", "INTERN")
                                .anyRequest().authenticated()
                )
                .httpBasic(withDefaults()); // Use basic authentication, you can further customize it here

        http.csrf().disable(); // Disable CSRF for simplicity; you can enable it if needed

        return http.build();
    }
}