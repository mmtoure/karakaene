package com.mmt.karakaene.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){
        try {
            return httpSecurity
                    .csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests(authorize ->

                    authorize
                            .requestMatchers(HttpMethod.POST,"/register").permitAll()
                            .requestMatchers(HttpMethod.POST,"/activation").permitAll()
                            .requestMatchers(HttpMethod.POST, "/login").permitAll()
                            .anyRequest().authenticated()

            ).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
}

