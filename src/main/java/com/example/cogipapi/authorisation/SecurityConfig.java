package com.example.cogipapi.authorisation;
import com.example.cogipapi.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new CustomUserDetailsService(userRepository);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        RequestMatcher adminRequests = new OrRequestMatcher(
                new AntPathRequestMatcher("/api/admin/**"),
                new AntPathRequestMatcher("/api/accountant/**"),
                new AntPathRequestMatcher("/api/intern/**")
        );

        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(adminRequests).hasAuthority("admin")
                                .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults()); // Use basic authentication, you can further customize it here

        return http.build();
    }
}