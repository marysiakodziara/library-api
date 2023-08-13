package com.example.libraryapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri("https://library-login.us.auth0.com/.well-known/jwks.json").build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors(withDefaults())
            .authorizeHttpRequests(authorize -> authorize
            .requestMatchers(HttpMethod.POST, "/api/v1/book")
            .authenticated()
            .requestMatchers(HttpMethod.PUT, "/api/v1/book/updateBook")
            .authenticated()
            .requestMatchers("/api/v1/client")
            .authenticated()
            .requestMatchers("/api/v1/client/**")
            .authenticated()
            .requestMatchers("/api/v1/reservation")
            .authenticated()
            .requestMatchers("/api/v1/reservation/**")
            .authenticated()
            .anyRequest()
            .permitAll()
            )
            .oauth2ResourceServer((oauth2ResourceServer) ->
                    oauth2ResourceServer
                            .jwt((jwt) ->
                                    jwt
                                            .decoder(jwtDecoder())
                            )
            );
        return http.build();
    }
}
