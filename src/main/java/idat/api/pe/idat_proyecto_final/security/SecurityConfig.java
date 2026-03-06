package idat.api.pe.idat_proyecto_final.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import idat.api.pe.idat_proyecto_final.service.impl.DetalleUsuarioService;

@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {
    private final @Lazy DetalleUsuarioService detalleUsuarioService;
    private final BCryptPasswordEncoder passwordEncoder;
    
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, IJwtService jwt) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        auth -> auth
                                // Endpoints públicos
                                .requestMatchers(HttpMethod.POST, "/api/v1/auth/login", "/api/v1/auth/register")
                                .permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/v1/hello/public")
                                .permitAll()
                                
                                // Endpoints para estudiantes (requieren autenticación)
                                .requestMatchers("/api/v1/tareas/**")
                                .hasRole("ESTUDIANTE")
                                .requestMatchers("/api/v1/asignaturas/**") 
                                .hasRole("ESTUDIANTE")
                                .requestMatchers("/api/v1/evaluaciones/**")
                                .hasRole("ESTUDIANTE")
                                .requestMatchers("/api/v1/ingresos/**")
                                .hasRole("ESTUDIANTE") 
                                .requestMatchers("/api/v1/gastos/**")
                                .hasRole("ESTUDIANTE")
                                .requestMatchers("/api/v1/usuarios/perfil")
                                .hasRole("ESTUDIANTE")
                                
                                // Cualquier otra petición requiere autenticación
                                .anyRequest().authenticated())
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(new FiltroJwtAuth(jwt),
                        UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(detalleUsuarioService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    @Bean
    AuthenticationManager authManager(AuthenticationConfiguration conf) throws Exception {
        return conf.getAuthenticationManager();
    }
}
