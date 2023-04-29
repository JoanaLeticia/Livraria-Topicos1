package com.livraria.eaglebookstore.resource;

import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.livraria.eaglebookstore.application.Result;
import com.livraria.eaglebookstore.dto.EnderecoDTO;
import com.livraria.eaglebookstore.dto.EnderecoResponseDTO;
import com.livraria.eaglebookstore.service.EnderecoService;
import com.oracle.svm.core.annotate.Inject;

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
