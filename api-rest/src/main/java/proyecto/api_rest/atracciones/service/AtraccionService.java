package proyecto.api_rest.atracciones.service;

import proyecto.api_rest.atracciones.model.Atraccion;
import proyecto.api_rest.atracciones.repository.AtraccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AtraccionService {

    @Autowired
    private AtraccionRepository atraccionRepository;

    // --- Métodos de Lectura (Queries) ---

    public List<Atraccion> obtenerTodas() {
        return atraccionRepository.findAll();
    }

    public Optional<Atraccion> obtenerPorId(Integer id) {
        return atraccionRepository.findById(id);
    }

    // --- Métodos de Escritura (Mutations) ---

    /**
     * Guarda una nueva atracción o actualiza una existente (si tiene ID).
     */
    public Atraccion guardarAtraccion(Atraccion atraccion) {
        // En un caso real, aquí irían validaciones de negocio antes de guardar
        return atraccionRepository.save(atraccion);
    }

    /**
     * Busca una atracción por ID y actualiza solo su estado.
     * Lanza un error si la atracción no existe.
     */
    public Atraccion actualizarEstado(Integer id, Boolean nuevoEstado) {
        // 1. Buscar la atracción existente
        Optional<Atraccion> atraccionOpt = atraccionRepository.findById(id);

        if (atraccionOpt.isEmpty()) {
            // Manejo de error si la atracción no se encuentra
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Atracción con ID " + id + " no encontrada para actualizar el estado.");
        }

        Atraccion atraccion = atraccionOpt.get();
        
        // 2. Aplicar el cambio de estado
        atraccion.setEstado(nuevoEstado);
        
        // 3. Guardar y devolver el objeto actualizado
        return atraccionRepository.save(atraccion);
    }
}