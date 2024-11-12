package com.megalabsapi.config;

import com.megalabsapi.security.JWTConfigurer;
import com.megalabsapi.security.JWTFilter;
import com.megalabsapi.security.JwtAuthenticationEntryPoint;
import com.megalabsapi.security.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;



import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig{

    private final TokenProvider tokenProvider;
    private final JWTFilter jwtRequestFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults()) // Habilitar CORS
                .csrf(AbstractHttpConfigurer::disable) // Deshabilitar CSRF en APIs REST
                .authorizeHttpRequests(authorize -> authorize
                        // Permitir acceso a los endpoints de registro y login sin autenticación
                        .requestMatchers(antMatcher("/auth/login")).permitAll()
                        .requestMatchers(antMatcher("/auth/register")).permitAll()
                        .requestMatchers(antMatcher("/auth/recover-password")).permitAll()
                        .requestMatchers(antMatcher("/auth/verify-code")).permitAll()
                        .requestMatchers(antMatcher("/calendario/**")).permitAll()
                        .requestMatchers(antMatcher("/control-calidad/**")).permitAll()
                        .requestMatchers(antMatcher("/entrega-muestra/**")).permitAll()
                        .requestMatchers(antMatcher("/citas/**")).permitAll()
                        .requestMatchers(antMatcher("/ordenes/**")).permitAll()
                        .requestMatchers(antMatcher("/productos/**")).permitAll()
                        .requestMatchers(antMatcher("/catalogo/**")).permitAll()
                        .requestMatchers(antMatcher("/admin/**")).permitAll()
                        .requestMatchers(antMatcher("/programacion/**")).permitAll()
                        .requestMatchers(antMatcher("/ventas/correo/**")).permitAll()
                        .requestMatchers("/api/v1/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**", "/webjars/**").permitAll()
                        // Cualquier otra solicitud requiere autenticación
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(AbstractHttpConfigurer::disable)
                .exceptionHandling(e -> e.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .sessionManagement(h -> h.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .with(new JWTConfigurer(tokenProvider), Customizer.withDefaults());

        // Agregar el filtro JWT antes de UsernamePasswordAuthenticationFilter
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Configuración de CORS para permitir solicitudes de localhost:3000
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("http://localhost:*")); // Permite cualquier puerto en localhost
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Métodos permitidos
        configuration.setAllowedHeaders(List.of("*")); // Permite todos los encabezados
        configuration.setAllowCredentials(true); // Permite enviar cookies o credenciales

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Aplica CORS a todos los endpoints
        return source;
    }
}

