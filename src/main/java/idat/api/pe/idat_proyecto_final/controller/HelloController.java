package idat.api.pe.idat_proyecto_final.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hello")
public class HelloController {

    @GetMapping
    @PreAuthorize("hasRole('ESTUDIANTE')")
    public ResponseEntity<String> getWelcome(){
        return ResponseEntity.ok(
                "¡Bienvenido al sistema de gestión de tareas académicas y financieras!");
    }
    
    @GetMapping("/public")
    public ResponseEntity<String> getPublicMessage(){
        return ResponseEntity.ok(
                "Este es un endpoint público - no requiere autenticación");
    }
}