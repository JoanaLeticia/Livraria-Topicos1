package com.livraria.eaglebookstore.resource;

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

import com.livraria.eaglebookstore.model.Endereco;
import com.livraria.eaglebookstore.service.EnderecoService;

@Path("/enderecos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnderecoResource {

    private final EnderecoService enderecoService;

    public EnderecoResource(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GET
    @Path("/{id}")
    public Endereco buscarEnderecoPorId(@PathParam("id") Long id) {
        return enderecoService.buscarEnderecoPorId(id);
    }

    @POST
    @Transactional
    public Endereco cadastrarEndereco(@Valid Endereco endereco) {
        return enderecoService.cadastrarEndereco(endereco);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public void atualizarEndereco(@PathParam("id") Long id, @Valid Endereco enderecoAtualizado) {
        enderecoService.atualizarEndereco(id, enderecoAtualizado);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void excluirEndereco(@PathParam("id") Long id) {
        enderecoService.excluirEndereco(id);
    }

}
