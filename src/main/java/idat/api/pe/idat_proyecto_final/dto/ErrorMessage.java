package idat.api.pe.idat_proyecto_final.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data

// Clase para manejar los mensajes de error

public class ErrorMessage {

    private Integer statusCode;

    private String message;

    private String description;
}
