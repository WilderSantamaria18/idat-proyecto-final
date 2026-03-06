package idat.api.pe.idat_proyecto_final.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "evaluacion",
       uniqueConstraints = @UniqueConstraint(columnNames = {"asignatura_id", "ponderacion_id"}))
public class Evaluacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asignatura_id", nullable = false)
    private Asignatura asignatura;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ponderacion_id", nullable = false, unique = true)
    private Ponderacion ponderacion;
    
    @Column(length = 100, nullable = false)
    private String nombre;
    
    @Column(precision = 4, scale = 2, nullable = false)
    private BigDecimal nota;
    
    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;
    
    @PrePersist
    public void prePersist() {
        if (fechaRegistro == null) {
            fechaRegistro = LocalDateTime.now();
        }
    }
}