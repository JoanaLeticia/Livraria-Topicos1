package com.livraria.eaglebookstore.resource;

import java.util.List;

import com.livraria.eaglebookstore.dto.UsuarioResponseDTO;
import com.livraria.eaglebookstore.service.UsuarioService;
import com.oracle.svm.core.annotate.Inject;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResource {
    
    @Inject
    UsuarioService usuarioService;

    @GET
    public List<UsuarioResponseDTO> getAll() {
        return usuarioService.getAll();
    }

    @GET
    @Path("/{id}")
    public UsuarioResponseDTO findById(@PathParam("id") Long id) {
        return usuarioService.findById(id);
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        usuarioService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Path("/count")
    public long count(){
        return usuarioService.count();
    }
}
