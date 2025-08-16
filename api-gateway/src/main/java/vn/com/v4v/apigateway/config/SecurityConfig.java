package vn.com.v4v.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public CorsConfigurationSource corsConfiguration() {

        UrlBasedCorsConfigurationSource cors = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setAllowedMethods(List.of("*"));
        corsConfiguration.setAllowedOrigins(List.of("*", "http://172.21.176.1:5172"));
        cors.registerCorsConfiguration("/**", corsConfiguration);
        return cors;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.csrf(CsrfConfigurer::disable);
        http.cors(cors -> cors.configurationSource(corsConfiguration()));
        http.authorizeHttpRequests(auth -> {
           auth.requestMatchers(HttpMethod.POST, "/api/v1/auth/**").permitAll()
                   .anyRequest().authenticated();
        });
        return http.build();
    }
}