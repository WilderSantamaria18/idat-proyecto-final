package idat.api.pe.idat_proyecto_final.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import idat.api.pe.idat_proyecto_final.dto.ErrorMessage;
import idat.api.pe.idat_proyecto_final.dto.GenericResponse;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResponse<Object>> handleGenericException(Exception ex) {
        ErrorMessage errorMsg = ErrorMessage.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .dateError(LocalDateTime.now())
                .message("Error interno del servidor")
                .description(ex.getMessage())
                .build();
        
        GenericResponse<Object> response = GenericResponse.<Object>builder()
                .error(errorMsg)
                .build();
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
    
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<GenericResponse<Object>> handleRuntimeException(RuntimeException ex) {
        ErrorMessage errorMsg = ErrorMessage.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .dateError(LocalDateTime.now())
                .message("Error en la solicitud")
                .description(ex.getMessage())
                .build();
        
        GenericResponse<Object> response = GenericResponse.<Object>builder()
                .error(errorMsg)
                .build();
        
        return ResponseEntity.badRequest().body(response);
    }
}