package com.devendra.spingboot.demosecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.security.Security;

@Configuration
public class DemoSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails dev = User.builder()
                .username("dev")
                .password("{noop}dev123")
                .roles("EMPLOYEE")
                .build();

        UserDetails prem = User.builder()
                .username("prem")
                .password("{noop}prem456")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails asha = User.builder()
                .username("asha")
                .password("{noop}asha789")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(dev, prem, asha);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer ->
                configurer.anyRequest().authenticated())
                .formLogin(form ->
                        form.loginPage("/showLoginForm").loginProcessingUrl("/authenticateTheUser").permitAll()
                );

        return http.build();
    }
}
