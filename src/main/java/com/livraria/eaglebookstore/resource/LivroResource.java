package com.livraria.eaglebookstore.resource;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import com.livraria.eaglebookstore.model.Livro;
import com.livraria.eaglebookstore.service.LivroService;

@Path("/livros")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LivroResource {

    @Inject
    LivroService livroService;

    @GET
    public List<Livro> listarLivros() {
        return livroService.listarLivros();
    }

    @GET
    @Path("/{id}")
    public Livro buscarLivroPorId(@PathParam("id") Long id) {
        return livroService.buscarLivroPorId(id);
    }

    @POST
    public Response cadastrarLivro(@Valid Livro livro) {
        livroService.cadastrarLivro(livro);
        return Response.status(Response.Status.CREATED).entity(livro).build();
    }

    @PUT
    @Path("/{id}")
    public Livro atualizarLivro(@PathParam("id") Long id, @Valid Livro livro) {
        return livroService.atualizarLivro(id, livro);
    }

    @DELETE
    @Path("/{id}")
    public Response excluirLivro(@PathParam("id") Long id) {
        livroService.excluirLivro(id);
        return Response.noContent().build();
    }
}