package proyecto.api_graphql.resolver;

import proyecto.api_graphql.model.Atraccion;
import proyecto.api_graphql.repository.AtraccionRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.NoSuchElementException;

@Component
public class AtraccionMutationResolver implements GraphQLMutationResolver {

    private final AtraccionRepository atraccionRepository;

    public AtraccionMutationResolver(AtraccionRepository atraccionRepository) {
        this.atraccionRepository = atraccionRepository;
    }

    public record NuevaAtraccionInput(String nombre, String codigo, String fechaInauguracion, String descripcion) {}

    public Atraccion crearAtraccion(NuevaAtraccionInput input) {
        Atraccion nueva = new Atraccion();
        nueva.setNombre(input.nombre());
        nueva.setCodigo(input.codigo());
        nueva.setDescripcion(input.descripcion());
        
        if (input.fechaInauguracion() != null) {
             nueva.setFechaInauguracion(LocalDate.parse(input.fechaInauguracion()));
        }
        
        nueva.setUltimaRevision(LocalDate.now());
        nueva.setEstado(true); 
        
        return atraccionRepository.save(nueva);
    }
    
    public Atraccion actualizarEstado(Integer id, Boolean nuevoEstado) {
        Atraccion atraccion = atraccionRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Atracción no encontrada con ID: " + id));
            
        atraccion.setEstado(nuevoEstado);
        atraccion.setUltimaRevision(LocalDate.now());
        
        return atraccionRepository.save(atraccion);
    }
}