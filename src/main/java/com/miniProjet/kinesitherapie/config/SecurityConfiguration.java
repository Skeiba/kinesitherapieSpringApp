package com.miniProjet.kinesitherapie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

//@Configuration
public class SecurityConfiguration {

    //@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(c -> {
                    CorsConfigurationSource source = request -> {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(
                                List.of("http://localhost:3000", "http://localhost:3001"));
                        config.setAllowedMethods(
                                List.of("GET", "POST", "PUT", "DELETE"));
                        config.setAllowedHeaders(List.of("*"));
                        return config;
                    };
                    c.configurationSource(source);
                })
                .authorizeHttpRequests(
                        req->req.requestMatchers("/api/**","/api/salles/{id}", "/refresh_token/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated());

        return http.build();
    }
}


