package com.losveganos.api.atracciones.repository;

import com.losveganos.api.atracciones.model.Atraccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// JpaRepository da métodos CRUD automáticos
public interface AtraccionRepository extends JpaRepository<Atraccion, Integer> {
    
    // Ejemplo de un método de búsqueda personalizado (Spring Data JPA lo implementa solo)
    List<Atraccion> findByEstado(Boolean estado);
}