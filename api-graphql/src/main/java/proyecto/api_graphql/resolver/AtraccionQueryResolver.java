package proyecto.api_graphql.resolver;

import proyecto.api_graphql.model.Atraccion;
import proyecto.api_graphql.repository.AtraccionRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class AtraccionQueryResolver implements GraphQLQueryResolver {

    private final AtraccionRepository atraccionRepository;

    public AtraccionQueryResolver(AtraccionRepository atraccionRepository) {
        this.atraccionRepository = atraccionRepository;
    }

    public List<Atraccion> todasAtracciones() {
        return atraccionRepository.findAll();
    }

    public Atraccion atraccionPorId(Integer id) {
        return atraccionRepository.findById(id).orElse(null);
    }
}