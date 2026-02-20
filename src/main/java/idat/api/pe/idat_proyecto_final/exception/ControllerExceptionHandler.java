package idat.api.pe.idat_proyecto_final.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import idat.api.pe.idat_proyecto_final.dto.ErrorMessage;
import idat.api.pe.idat_proyecto_final.dto.GenericResponse;

@RestControllerAdvice

// Clase para manejar las excepciones

public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public GenericResponse<Object> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {

        ErrorMessage errorMessage = ErrorMessage.builder()
                .statusCode(404)
                .message(ex.getMessage())
                .description(request.getDescription(true))
                .build();

        return GenericResponse.builder()
                .errorMessage(errorMessage)
                .build();
    }

}
