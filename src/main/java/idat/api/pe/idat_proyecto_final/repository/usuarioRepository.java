package idat.api.pe.idat_proyecto_final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import idat.api.pe.idat_proyecto_final.model.Usuario;

public interface usuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByEmail(String email);
    boolean existsByEmail(String email);
}
