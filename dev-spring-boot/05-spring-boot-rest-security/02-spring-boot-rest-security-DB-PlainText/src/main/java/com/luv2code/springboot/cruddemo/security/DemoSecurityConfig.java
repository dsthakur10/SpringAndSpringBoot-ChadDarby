package com.luv2code.springboot.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

    // Add support for JDBC --> no more hard-coded users

    // Inject datasource --> auto-configured by Spring Boot
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        return new JdbcUserDetailsManager(dataSource);
        // Spring Boot knows where to look in JDBC datasource as we have followed a predefined schema
        // to create the tables for Spring security --> built-in functionality
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
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails devendra = User.builder()
                .username("devendra")
                .password("{noop}devendra")
                .roles("EMPLOYEE")
                .build();

        UserDetails thakur = User.builder()
                .username("thakur")
                .password("{noop}thakur")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails premsingh = User.builder()
                .username("premsingh")
                .password("{noop}premsingh")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(devendra, thakur, premsingh);
    }
*/


/*
Provide role based authorizations:
    EMPLOYEE --> READ
    MANAGER --> CREATE / UPDATE
    ADMIN --> DELETE

    MANAGER is superset of EMPLOYEE
    ADMIN is superset of MANAGER
 */