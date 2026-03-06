package idat.api.pe.idat_proyecto_final.service;

import idat.api.pe.idat_proyecto_final.model.Usuario;
import idat.api.pe.idat_proyecto_final.dto.RegistroUsuario;

public interface IUsuarioService {
    Usuario getByEmail(String email);
    boolean existsByEmail(String email);
    Usuario registrarUsuario(RegistroUsuario registroUsuario);
}
