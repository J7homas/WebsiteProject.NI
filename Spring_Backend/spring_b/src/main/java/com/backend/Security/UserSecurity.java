package com.backend.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class UserSecurity {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorizeRequests -> 
                authorizeRequests
                    .requestMatchers("/admin/**").hasRole("ADMIN")  // Admin-specific paths
                    .requestMatchers("/user/**").hasRole("USER")   // User-specific paths
                    .anyRequest().authenticated()
            )
            .formLogin(form -> form  // Custom login page can be specified here
                .loginPage("/login")
                .permitAll()
            )
            .logout(logout -> logout  // Logout configuration
                .logoutUrl("/logout")
                .permitAll()
            );
        return http.build();
    }

    @Bean
    UserDetailsService userDetailsService() {
        UserDetails admin = org.springframework.security.core.userdetails.User.builder()
            .username("admin")
            .password(passwordEncoder().encode("admin123"))  // Secure password for admin
            .roles("ADMIN")
            .build();

        UserDetails user = org.springframework.security.core.userdetails.User.builder()
            .username("user")
            .password(passwordEncoder().encode("password"))
            .roles("USER")
            .build();

        return new InMemoryUserDetailsManager(admin, user);  // Multiple users
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Use BCrypt for password encoding
    }
}

