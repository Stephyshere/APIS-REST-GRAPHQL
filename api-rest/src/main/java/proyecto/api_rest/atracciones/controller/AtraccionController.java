package proyecto.api_rest.atracciones.controller;

import proyecto.api_rest.atracciones.model.Atraccion;
import proyecto.api_rest.atracciones.service.AtraccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

// Cambiamos @Controller por @RestController para que Spring sepa que maneja peticiones REST
@RestController
// Definimos el endpoint base para todas las rutas de este controlador
@RequestMapping("/api/v1/atracciones") 
public class AtraccionController {

    @Autowired
    private AtraccionService atraccionService;

    // ----------------------------------------
    // ENDPOINTS DE LECTURA (GET)
    // ----------------------------------------
    
    // GET /api/v1/atracciones
    // Retorna una lista de todas las Atracciones
    @GetMapping
    public List<Atraccion> obtenerTodasAtracciones() {
        return atraccionService.obtenerTodas();
    }

    // GET /api/v1/atracciones/{id}
    // Retorna una Atraccion por su ID o un 404
    @GetMapping("/{id}")
    public ResponseEntity<Atraccion> obtenerAtraccionPorId(@PathVariable Integer id) {
        Optional<Atraccion> atraccion = atraccionService.obtenerPorId(id);
        
        // Retorna 200 OK con el cuerpo si existe, o 404 NOT FOUND si no existe
        return atraccion.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build());
    }


    // ----------------------------------------
    // ENDPOINTS DE ESCRITURA (POST / PUT)
    // ----------------------------------------
    
    // POST /api/v1/atracciones
    // Crea una nueva atracción.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Retorna 201 Created
    public Atraccion crearAtraccion(@RequestBody Atraccion atraccion) {
        // En REST, la creación y actualización se suelen separar. 
        // Aquí usamos el método guardarAtraccion que manejará ambos casos según el ID.
        return atraccionService.guardarAtraccion(atraccion);
    }
    
    // PUT /api/v1/atracciones/{id}/estado
    // Actualiza solo el estado de una atracción específica
    @PutMapping("/{id}/estado")
    public Atraccion actualizarEstadoAtraccion(
        @PathVariable Integer id, 
        @RequestBody Boolean nuevoEstado) { 
        
        // El método actualizarEstado en el Service ya maneja el caso de ID no encontrado.
        try {
            return atraccionService.actualizarEstado(id, nuevoEstado);
        } catch (ResponseStatusException ex) {
            // Re-lanzamos la excepción del Service para que Spring la maneje y retorne el 404
            throw ex;
        }
    }
    
    // PUT /api/v1/atracciones/{id}
    // Actualiza todos los campos de una atracción existente.
    @PutMapping("/{id}")
    public Atraccion actualizarAtraccionCompleta(
        @PathVariable Integer id, 
        @RequestBody Atraccion atraccionDetalles) {
        
        // Aseguramos que el ID del path se use para la actualización
        atraccionDetalles.setId(id);
        
        // Validamos que exista antes de guardar (para devolver 404 si no existe)
        if (atraccionService.obtenerPorId(id).isEmpty()) {
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Atracción con ID " + id + " no encontrada para actualizar.");
        }
        
        // Llamamos al método guardarAtraccion, que actuará como update.
        return atraccionService.guardarAtraccion(atraccionDetalles);
    }
}
