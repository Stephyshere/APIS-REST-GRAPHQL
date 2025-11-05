package proyecto.api_graphql.repository;

import proyecto.api_graphql.model.Atraccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtraccionRepository extends JpaRepository<Atraccion, Integer> {
    // Hereda todos los métodos CRUD básicos
}