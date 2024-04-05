package com.avijit.appointmentmanagementsystem.Config.Security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

 @Bean
    public SecurityFilterChain defaultSecurity(HttpSecurity http) throws Exception {

     http.authorizeHttpRequests((authorize)->authorize
             .requestMatchers("/api/v1/user/register").permitAll()
             .anyRequest().permitAll()).
             cors().and().csrf().disable();

     return http.build();
 }
}
