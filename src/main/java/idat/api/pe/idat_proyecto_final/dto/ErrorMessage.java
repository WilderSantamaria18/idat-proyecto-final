package idat.api.pe.idat_proyecto_final.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ErrorMessage {
    private Integer statusCode;
    private LocalDateTime dateError;
    private String message;
    private String description;
}
