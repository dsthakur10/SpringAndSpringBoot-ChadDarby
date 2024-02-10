package com.luv2code.springboot.cruddemo.security;

import com.luv2code.springboot.cruddemo.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

    //beans
    //bcrypt bean definition
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
    UserDetails devendra = User.builder()
                .username("devendra")
                .password("{bcrypt}devendra")
                .roles("EMPLOYEE")
                .build();

    Here, DB does not have {noop}/{bcrypt} in the password column of DB table
    We have just stored encrypted passwords in DB
    Now, it's our responsibility to make the Spring Security match the stored encrypted password in DB
    with the incoming plain-text password entered by user at the time of Login

    For this, we have used BCryptPasswordEncoder() of Spring security class.

    */

    //authenticationProvider bean definition
    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserService userService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService); //set the custom user details service
        auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
        return auth;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer ->
                    configurer
                            .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                            .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                            .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                            .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                            .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN"));

        // use HTTP Basic authentication
        http.httpBasic();

        // disable Cross Site Request Forgery (CSRF)
        // in general not required for stateless REST APIs that use POST, PUT, DELETE and/or PATCH
        http.csrf().disable();

        return http.build();
    }
}


/*

authenticationProvider() method:
    Responsible for defining and configuring a DaoAuthenticationProvider.
    A DaoAuthenticationProvider is a class provided by Spring Security that performs user authentication
    by delegating to a data access object (DAO) and a user details service.

Here's a breakdown of what this method is doing:

    DaoAuthenticationProvider auth = new DaoAuthenticationProvider();:
    --> This line creates a new instance of DaoAuthenticationProvider, which will be used to configure
        the authentication process.

    auth.setUserDetailsService(userService) :
    --> This line sets the custom user details service (userService) for the DaoAuthenticationProvider.
    --> The user details service is responsible for retrieving user information from a data source
        (e.g., a database) and providing it to the authentication provider.
    --> In this case, userService is expected to implement Spring Security's UserDetailsService interface,
        which typically loads user details based on a username.

    auth.setPasswordEncoder(passwordEncoder()) :
    --> This line sets the password encoder for the DaoAuthenticationProvider.
    --> The passwordEncoder() method is another Spring bean defined in this configuration class,
        and it returns a BCryptPasswordEncoder.
    --> The password encoder is used to hash and verify user passwords securely.
    --> By setting it to use BCrypt, passwords will be hashed using the BCrypt algorithm.

*/

/*

SUMMARY:
    In summary, the authenticationProvider() method creates and configures a DaoAuthenticationProvider
    by setting the user details service and password encoder.
    This provider is essential for authenticating users during the login process.
    When a user attempts to log in, Spring Security will delegate the authentication process to this
    DaoAuthenticationProvider, which will use the userService to retrieve user details and
    the BCryptPasswordEncoder to verify the password's validity.

*/