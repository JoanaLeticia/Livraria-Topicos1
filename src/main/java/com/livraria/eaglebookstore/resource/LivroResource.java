package com.livraria.eaglebookstore.resource;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.livraria.eaglebookstore.model.Livro;
import com.livraria.eaglebookstore.service.LivroService;

@Path("/livros")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LivroResource {

    @Inject
    LivroService livroService;

    @GET
    @Path("/{id}")
    public Livro buscarLivroPorId(@PathParam("id") Long id) {
        return livroService.buscarLivroPorId(id);
    }

    @POST
    @Transactional
    public Livro cadastrarLivro(@Valid Livro livro) {
        return livroService.save(livro);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Livro atualizarLivro(@PathParam("id") Long id, @Valid Livro livro) {
        return livroService.atualizarLivro(id, livro);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void excluirLivro(@PathParam("id") Long id) {
        livroService.excluirLivro(id);
    }

}
