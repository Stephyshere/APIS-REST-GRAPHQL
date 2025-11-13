package proyecto.api_rest.atracciones.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import java.time.LocalDate;

@Entity
@Table(name = "atracciones") 
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

    // ------------------------------------
    // 1. CONSTRUCTORES (Recomendado para JPA)
    // ------------------------------------

    // Constructor vacío (necesario para JPA/Hibernate)
    public Atraccion() {
    }

    // Constructor con todos los campos (excepto ID, que es autogenerado)
    public Atraccion(String nombre, String codigo, LocalDate fechaInauguracion, LocalDate ultimaRevision, String descripcion, Boolean estado) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.fechaInauguracion = fechaInauguracion;
        this.ultimaRevision = ultimaRevision;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    // ------------------------------------
    // 2. GETTERS
    // ------------------------------------

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public LocalDate getFechaInauguracion() {
        return fechaInauguracion;
    }

    public LocalDate getUltimaRevision() {
        return ultimaRevision;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Boolean getEstado() {
        return estado;
    }

    // ------------------------------------
    // 3. SETTERS
    // ------------------------------------

    // Nota: El setter de 'id' raramente se usa, pero se incluye por completitud.
    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setFechaInauguracion(LocalDate fechaInauguracion) {
        this.fechaInauguracion = fechaInauguracion;
    }

    public void setUltimaRevision(LocalDate ultimaRevision) {
        this.ultimaRevision = ultimaRevision;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}