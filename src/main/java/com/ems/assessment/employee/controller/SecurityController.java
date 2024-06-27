package com.ems.assessment.employee.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityController {
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(registry -> {
                registry.antMatchers("/api/home").permitAll();
                registry.antMatchers("/api/employeeDetails").authenticated();
                registry.anyRequest().authenticated();
            })
            .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
            .build();
}
}

