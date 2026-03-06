package idat.api.pe.idat_proyecto_final.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import idat.api.pe.idat_proyecto_final.dto.*;
import idat.api.pe.idat_proyecto_final.model.Usuario;
import idat.api.pe.idat_proyecto_final.security.IJwtService;
import idat.api.pe.idat_proyecto_final.service.impl.DetalleUsuarioService;
import idat.api.pe.idat_proyecto_final.service.impl.UsuarioService;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private final UsuarioService usuarioService;
    private final DetalleUsuarioService detalleUsuarioService;
    private final IJwtService jwtService;
    private final AuthenticationManager authManager;
    
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$"
    );

    @PostMapping("/login")
    public ResponseEntity<GenericResponse<UsuarioJwt>> login(@RequestBody Login login){
        GenericResponse<UsuarioJwt> response;
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            login.getEmail(),
                            login.getPassword()));
            
            Usuario usuario = usuarioService.getByEmail(login.getEmail());
            String token = jwtService.generarToken(usuario,
                    detalleUsuarioService.getAuthorities(usuario));
            
            UsuarioJwt usuarioJwt = UsuarioJwt.builder()
                    .id(usuario.getId())
                    .nombre(usuario.getNombre())
                    .email(usuario.getEmail())
                    .token(token).build();
            
            response = GenericResponse.<UsuarioJwt>builder()
                    .response(usuarioJwt).build();
            return ResponseEntity.ok(response);
            
        }catch (AuthenticationException e){
            response = GenericResponse.<UsuarioJwt>builder()
                    .error(ErrorMessage.builder()
                            .statusCode(HttpStatus.UNAUTHORIZED.value())
                            .dateError(LocalDateTime.now())
                            .message("Email y/o contraseña incorrectos")
                            .description("Las credenciales proporcionadas son inválidas")
                            .build()).build();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(response);
        }
    }
    
    @PostMapping("/register")
    public ResponseEntity<GenericResponse<UsuarioJwt>> register(@RequestBody RegistroUsuario registroUsuario){
        GenericResponse<UsuarioJwt> response;
        
        try {
            // Validar formato de email
            if (!EMAIL_PATTERN.matcher(registroUsuario.getEmail()).matches()) {
                response = GenericResponse.<UsuarioJwt>builder()
                        .error(ErrorMessage.builder()
                                .statusCode(HttpStatus.BAD_REQUEST.value())
                                .dateError(LocalDateTime.now())
                                .message("Formato de email inválido")
                                .description("El email proporcionado no tiene un formato válido")
                                .build()).build();
                return ResponseEntity.badRequest().body(response);
            }
            
            // Validar unicidad del email
            if (usuarioService.existsByEmail(registroUsuario.getEmail())) {
                response = GenericResponse.<UsuarioJwt>builder()
                        .error(ErrorMessage.builder()
                                .statusCode(HttpStatus.BAD_REQUEST.value())
                                .dateError(LocalDateTime.now())
                                .message("El email ya está registrado")
                                .description("Ya existe un usuario con este email")
                                .build()).build();
                return ResponseEntity.badRequest().body(response);
            }
            
            // Registrar usuario
            Usuario usuario = usuarioService.registrarUsuario(registroUsuario);
            String token = jwtService.generarToken(usuario,
                    detalleUsuarioService.getAuthorities(usuario));
            
            UsuarioJwt usuarioJwt = UsuarioJwt.builder()
                    .id(usuario.getId())
                    .nombre(usuario.getNombre())
                    .email(usuario.getEmail())
                    .token(token).build();
            
            response = GenericResponse.<UsuarioJwt>builder()
                    .response(usuarioJwt).build();
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
            
        } catch (Exception e) {
            response = GenericResponse.<UsuarioJwt>builder()
                    .error(ErrorMessage.builder()
                            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .dateError(LocalDateTime.now())
                            .message("Error interno del servidor")
                            .description("Ocurrió un error inesperado durante el registro")
                            .build()).build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}