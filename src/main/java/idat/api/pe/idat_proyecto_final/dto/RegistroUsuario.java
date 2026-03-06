package idat.api.pe.idat_proyecto_final.dto;

import lombok.Data;

@Data
public class RegistroUsuario {
    private String nombre;
    private String email;
    private String password;
}