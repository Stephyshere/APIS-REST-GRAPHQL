package proyecto.api_graphql.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "atracciones")
@Data 
public class Atraccion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String codigo;
    
    @Column(name = "fecha_inauguracion")
    private LocalDate fechaInauguracion;
    
    @Column(name = "ultima_revision")
    private LocalDate ultimaRevision;
    
    private String descripcion;
    private Boolean estado;
}