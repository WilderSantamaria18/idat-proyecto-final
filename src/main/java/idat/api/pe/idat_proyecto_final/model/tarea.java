package idat.api.pe.idat_proyecto_final.model;

import java.time.LocalDate;

import org.springframework.security.core.parameters.P;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name ="tarea")
@Entity
@Data
public class Tarea {
    
    @Id 
    @GeneratedValue(strategy = generationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descripcion;
    private LocalDate fechalimite;
     @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private PrioridadTarea prioridad = PrioridadTarea.MEDIA;

    @Enumerated(EnumType.STRING)
    @Column(length = 12)
    private EstadoTarea estado = EstadoTarea.PENDIENTE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "google_event_id", length = 255)
    private String googleEventId;


}
