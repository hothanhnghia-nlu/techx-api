package vn.edu.hcmuaf.fit.api.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import vn.edu.hcmuaf.fit.api.security.filter.JwtAuthFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorized) -> authorized
                        .anyRequest().authenticated())
                .sessionManagement((sessionManager) -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//                .oauth2Login(oauth2Login ->
//                        oauth2Login.userInfoEndpoint(userInfoEndpoint ->
//                                userInfoEndpoint.oidcUserService(oidcUserService()))
//                )

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(IGNORED_ENDPOINTS);
    }
    public static final String[] IGNORED_ENDPOINTS = new String[]{
            "/api/v*/auth/**",
            "/api/v*/users/**",            // Authentication endpoints
            "/swagger-ui/**",              // Swagger UI resources
            "/v3/api-docs/**",             // OpenAPI 3 documentation
            "/v2/api-docs/**",             // Swagger 2 documentation
            "/swagger-resources/**",       // Swagger resources
            "/webjars/**",                 // Webjars (for Swagger UI)
            "/actuator/**",                // Spring Boot Actuator endpoints
            "/public/**",                  // Any public resources
            "/static/**",                  // Static resources (CSS, JS, images)
            "/css/**",                     // CSS resources
            "/js/**",                      // JavaScript resources
            "/images/**",                  // Image resources
            "/favicon.ico"                 // Favicon
    };
}