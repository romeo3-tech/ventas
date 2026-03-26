package com.example.ventas_service.confsec;

import com.example.ventas_service.security.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.Filter;

@Configuration
public class SecurityConfig {

    @Bean
    public Filter jwtFilter() {
        return new JwtFilter();
    }
}