package idat.api.pe.idat_proyecto_final.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenericResponse<T> {
    private T response;
    private ErrorMessage error;
}
