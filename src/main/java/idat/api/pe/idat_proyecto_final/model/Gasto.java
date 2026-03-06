package idat.api.pe.idat_proyecto_final.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "gasto")
public class Gasto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    
    @Column(length = 200, nullable = false)
    private String concepto;
    
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal monto;
    
    @Column(nullable = false)
    private LocalDate fecha;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoriaGasto categoria;
}