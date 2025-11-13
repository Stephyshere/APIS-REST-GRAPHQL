package proyecto.api_rest.atracciones.repository;

import proyecto.api_rest.atracciones.model.Atraccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// JpaRepository da métodos CRUD automáticos
public interface AtraccionRepository extends JpaRepository<Atraccion, Integer> {
    
    // Ejemplo de un método de búsqueda personalizado (Spring Data JPA lo implementa solo)
    List<Atraccion> findByEstado(Boolean estado);
}