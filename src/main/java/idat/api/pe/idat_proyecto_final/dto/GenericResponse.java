package idat.api.pe.idat_proyecto_final.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

// Clase para manejar las respuestas genéricas

public class GenericResponse<T> {

    // campo para almacenar la respuesta Exitosa o cualquier objeto
    private T response;

    // campo para almacenar el mensaje de error
    private ErrorMessage errorMessage;

}
