package com.livraria.eaglebookstore.resource;

import com.livraria.eaglebookstore.application.Result;
import com.livraria.eaglebookstore.dto.ClienteDTO;
import com.livraria.eaglebookstore.model.Cliente;
import com.livraria.eaglebookstore.service.ClienteService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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
    public Response atualizarCliente(@PathParam("id") Long id, ClienteDTO dto) {
        try{
            clienteService.atualizarCliente(id, dto);
            return Response.status(Status.NO_CONTENT).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void excluirCliente(@PathParam("id") Long id) {
        clienteService.excluirCliente(id);
    }
}