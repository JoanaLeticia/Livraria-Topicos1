package com.livraria.eaglebookstore.resource;

import com.livraria.eaglebookstore.application.Result;
import com.livraria.eaglebookstore.dto.ProdutoDTO;
import com.livraria.eaglebookstore.dto.ProdutoResponseDTO;
import com.livraria.eaglebookstore.service.ProdutoService;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import java.util.List;

@Path("/produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {

    @Inject
    ProdutoService produtoService;

    public ProdutoResource(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GET
    public List<ProdutoResponseDTO> listarProdutos() {
        return produtoService.listarProdutos();
    }

    @GET
    @Path("/{id}")
    public ProdutoResponseDTO buscarProdutoPorId(@PathParam("id") Long id) {
        return produtoService.buscarProdutoPorId(id);
    }

    @POST
    public Response cadastrarProduto(ProdutoDTO dto) {
        try{
            ProdutoResponseDTO produto = produtoService.cadastrarProduto(dto);
            return Response.status(Status.CREATED).entity(produto).build();
        } catch(ConstraintViolationException e){
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }
    
    @PUT
    @Path("/{id}")
    public Response atualizarProduto(@PathParam("id") Long id, ProdutoDTO dto) {
        try {
            produtoService.atualizarProduto(id, dto);
            return Response.status(Status.NO_CONTENT).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response excluirProduto(@PathParam("id") Long id) {
        produtoService.excluirProduto(id);
        return Response.status(Status.NO_CONTENT).build();
    }

}