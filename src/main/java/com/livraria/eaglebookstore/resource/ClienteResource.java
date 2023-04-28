package com.livraria.eaglebookstore.resource;

import com.livraria.eaglebookstore.model.Cliente;
import com.livraria.eaglebookstore.service.ClienteService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {

    @Inject
    ClienteService clienteService;

    @GET
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    @GET
    @Path("/{id}")
    public Cliente buscarClientePorId(@PathParam("id") Long id) {
        return clienteService.buscarClientePorId(id);
    }

    @POST
    @Transactional
    public Cliente cadastrarCliente(@Valid Cliente cliente) {
        return clienteService.cadastrarCliente(cliente);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Cliente atualizarCliente(@PathParam("id") Long id, @Valid Cliente clienteAtualizado) {
        return clienteService.atualizarCliente(id, clienteAtualizado);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void excluirCliente(@PathParam("id") Long id) {
        clienteService.excluirCliente(id);
    }
}