package com.losveganos.api.atracciones.graphql;

import com.losveganos.api.atracciones.model.Atraccion;
import com.losveganos.api.atracciones.service.AtraccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

// Este controlador ahora es un "Resolvers" para los tipos Query y Mutation definidos en el .graphqls
@Controller
public class AtraccionController {

    @Autowired
    private AtraccionService atraccionService;

    // ----------------------------------------
    // RESOLVERS PARA EL TIPO 'Query'
    // ----------------------------------------
    
    // Mapea el campo 'todasAtracciones' del tipo 'Query' del schema.graphqls
    @SchemaMapping(typeName = "Query", field = "todasAtracciones")
    public List<Atraccion> resolverTodasAtracciones() {
        return atraccionService.obtenerTodas();
    }

    // Mapea el campo 'atraccionPorId' del tipo 'Query' del schema.graphqls
    @SchemaMapping(typeName = "Query", field = "atraccionPorId")
    public Optional<Atraccion> resolverAtraccionPorId(@Argument Integer id) {
        return atraccionService.obtenerPorId(id);
    }


    // ----------------------------------------
    // RESOLVERS PARA EL TIPO 'Mutation'
    // ----------------------------------------
    
    // NOTA: Para Mutaciones, Spring te permite seguir usando @MutationMapping 
    // como un atajo más limpio que @SchemaMapping(typeName = "Mutation", ...), 
    // siempre que los nombres de los métodos coincidan con el esquema.

    /**
     * Mapea el campo 'guardarAtraccion' del tipo 'Mutation' del schema.graphqls
     */
    @MutationMapping
    public Atraccion guardarAtraccion(@Argument(name = "atraccionInput") Atraccion atraccion) {
        return atraccionService.guardarAtraccion(atraccion);
    }


    /**
     * Mapea el campo 'actualizarEstadoAtraccion' del tipo 'Mutation' del schema.graphqls
     */
    @MutationMapping
    public Atraccion actualizarEstadoAtraccion(
        @Argument Integer id, 
        @Argument Boolean nuevoEstado) {
            
        return atraccionService.actualizarEstado(id, nuevoEstado);
    }
}