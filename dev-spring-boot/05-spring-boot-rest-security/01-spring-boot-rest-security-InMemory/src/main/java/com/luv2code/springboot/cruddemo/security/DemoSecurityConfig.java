package com.luv2code.springboot.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails devendra = User.builder()
                .username("devendra")
                .password("{noop}devendra123")
                .roles("EMPLOYEE")
                .build();

        UserDetails thakur = User.builder()
                .username("thakur")
                .password("{noop}thakur456")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails prem = User.builder()
                .username("prem")
                .password("{noop}prem789")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(devendra, thakur, prem);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
        );

        // use HTTP Basic authentication
        http.httpBasic();

        // disable CSRF
        // In general, not required for stateless REST APIs that use POST, PUT, DELETE, PATCH
        http.csrf().disable();

        return http.build();
    }
}

/*
Provide role based authorizations:
    EMPLOYEE --> READ
    MANAGER --> CREATE / UPDATE
    ADMIN --> DELETE

    MANAGER is superset of EMPLOYEE
    ADMIN is superset of MANAGER
 */