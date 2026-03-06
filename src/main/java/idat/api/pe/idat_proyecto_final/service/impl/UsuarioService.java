package idat.api.pe.idat_proyecto_final.service.impl;

import idat.api.pe.idat_proyecto_final.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import idat.api.pe.idat_proyecto_final.dto.RegistroUsuario;
import idat.api.pe.idat_proyecto_final.model.RolUsuario;
import idat.api.pe.idat_proyecto_final.model.Usuario;

import idat.api.pe.idat_proyecto_final.service.IUsuarioService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UsuarioService implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Usuario getByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    @Override
    public Usuario registrarUsuario(RegistroUsuario registroUsuario) {
        Usuario usuario = new Usuario();
        usuario.setNombre(registroUsuario.getNombre());
        usuario.setEmail(registroUsuario.getEmail());
        usuario.setPassword(passwordEncoder.encode(registroUsuario.getPassword()));
        usuario.setRol(RolUsuario.ESTUDIANTE);
        usuario.setFechaCreacion(LocalDateTime.now());
        
        return usuarioRepository.save(usuario);
    }
}
