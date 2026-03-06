package idat.api.pe.idat_proyecto_final.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "ponderacion", 
       uniqueConstraints = @UniqueConstraint(columnNames = {"asignatura_id", "orden"}))
public class Ponderacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asignatura_id", nullable = false)
    private Asignatura asignatura;
    
    @Column(precision = 3, scale = 2, nullable = false)
    private BigDecimal porcentaje;
    
    @Column(nullable = false)
    private Integer orden;
    
    @OneToOne(mappedBy = "ponderacion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Evaluacion evaluacion;
}