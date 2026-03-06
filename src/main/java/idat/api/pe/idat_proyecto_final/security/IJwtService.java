package idat.api.pe.idat_proyecto_final.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.GrantedAuthority;
import idat.api.pe.idat_proyecto_final.model.Usuario;

import java.util.List;

public interface IJwtService {
    String generarToken(Usuario usuario, List<GrantedAuthority> authorities);
    Claims obtenerClaims(String token);
    boolean tokenValido(String token);
    String extraerToken(HttpServletRequest request);
    void generarAutenticacion(Claims claims);
}