package idat.api.pe.idat_proyecto_final.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "tarea")
public class Tarea {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 200, nullable = false)
    private String titulo;
    
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    
    @Column(name = "fecha_limite", nullable = false)
    private LocalDate fechaLimite;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PrioridadTarea prioridad = PrioridadTarea.MEDIA;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoTarea estado = EstadoTarea.PENDIENTE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "google_event_id", length = 255)
    private String googleEventId;
}
