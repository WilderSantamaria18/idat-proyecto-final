package idat.api.pe.idat_proyecto_final.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name ="tarea")
@Entity
@Data
public class tarea {
    
    @Id 
    @GeneratedValue(strategy = generationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descripcion;
    private LocalDate fechalimite;

}
