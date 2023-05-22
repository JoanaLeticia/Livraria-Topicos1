package com.livraria.eaglebookstore.resource;

import java.util.List;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.inject.Inject;

import com.livraria.eaglebookstore.application.Result;
import com.livraria.eaglebookstore.dto.EnderecoDTO;
import com.livraria.eaglebookstore.dto.EnderecoResponseDTO;
import com.livraria.eaglebookstore.service.EnderecoService;

@Path("/enderecos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnderecoResource {

    @Inject
    private final EnderecoService enderecoService;

    public EnderecoResource(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GET
    public List<EnderecoResponseDTO> listarEnderecos() {
        return enderecoService.listarEnderecos();
    }

    @GET
    @Path("/{id}")
    public EnderecoResponseDTO buscarEnderecoPorId(@PathParam("id") Long id) {
        return enderecoService.buscarEnderecoPorId(id);
    }

    @POST
    public Response cadastrarEndereco(EnderecoDTO dto) {
        try{
            EnderecoResponseDTO endereco = enderecoService.cadastrarEndereco(dto);
            return Response.status(Status.CREATED).entity(endereco).build();
        } catch(ConstraintViolationException e){
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarEndereco(@PathParam("id") Long id, EnderecoDTO dto) {
        try {
            enderecoService.atualizarEndereco(id, dto);
            return Response.status(Status.NO_CONTENT).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response excluirEndereco(@PathParam("id") Long id) {
        enderecoService.excluirEndereco(id);
        return Response.status(Status.NO_CONTENT).build();
    }

}
