package com.miniProjet.kinesitherapie.config;

import com.miniProjet.kinesitherapie.utils.CustomLogoutHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;


@Slf4j
@Configuration
@EnableJdbcHttpSession
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomLogoutHandler customLogoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
               /* .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                )*/

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
                                .authenticated())
                /*.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**", "/api/**").permitAll()
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/secretaire/**").hasRole("SECRETAIRE")
                        .anyRequest().authenticated()
                )*/
                .logout(logout -> logout
                        .logoutUrl("/api/auth/logout")
                        .addLogoutHandler(customLogoutHandler)
                        .logoutSuccessHandler((request, response, authentication) -> {
                            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                            response.getWriter().write("{\"message\": \"Logout successful\"}");
                        })
                        .invalidateHttpSession(true)
                        .deleteCookies("SESSION")
                )
                .securityContext(securityContext -> securityContext
                        .requireExplicitSave(true)
                );

        return http.build();
    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new HttpSessionSecurityContextRepository();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
