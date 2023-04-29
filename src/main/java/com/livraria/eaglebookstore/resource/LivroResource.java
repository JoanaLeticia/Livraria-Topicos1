package com.livraria.eaglebookstore.resource;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import java.util.List;

import com.livraria.eaglebookstore.application.Result;
import com.livraria.eaglebookstore.dto.LivroDTO;
import com.livraria.eaglebookstore.dto.LivroResponseDTO;
import com.livraria.eaglebookstore.service.LivroService;

@Path("/livros")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LivroResource {

    @Inject
    LivroService livroService;

    @GET
    public List<LivroResponseDTO> listarLivros() {
        return livroService.listarLivros();
    }

    @GET
    @Path("/{id}")
    public LivroResponseDTO buscarLivroPorId(@PathParam("id") Long id) {
        return livroService.buscarLivroPorId(id);
    }

    @POST
    public Response cadastrarLivro(LivroDTO dto) {
        try {
            LivroResponseDTO livro = livroService.cadastrarLivro(dto);
            return Response.status(Status.CREATED).entity(livro).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarLivro(@PathParam("id") Long id, LivroDTO dto) {
        try {
            livroService.atualizarLivro(id, dto);;
            return Response.status(Status.NO_CONTENT).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }


    @DELETE
    @Path("/{id}")
    public Response excluirLivro(@PathParam("id") Long id) {
        livroService.excluirLivro(id);
        return Response.status(Status.NO_CONTENT).build();
    }
    
}