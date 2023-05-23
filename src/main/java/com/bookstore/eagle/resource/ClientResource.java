package com.bookstore.eagle.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import java.util.List;

import com.bookstore.eagle.application.Result;
import com.bookstore.eagle.dto.ClientDTO;
import com.bookstore.eagle.dto.ClientResponseDTO;
import com.bookstore.eagle.service.ClientService;

@Path("/clients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientResource {

    @Inject
    ClientService clientService;

    @GET
    public List<ClientResponseDTO> listClients() {
        return clientService.listClients();
    }

    @GET
    @Path("/{id}")
    public ClientResponseDTO searchClientById(@PathParam("id") Long id) {
        return clientService.searchClientById(id);
    }

    @POST
    public Response addClient(ClientDTO dto) {
        try{
            ClientResponseDTO client = clientService.addClient(dto);
            return Response.status(Status.CREATED).entity(client).build();
        } catch(ConstraintViolationException e){
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }
    @PUT
    @Path("/{id}")
    public Response updateClient(@PathParam("id") Long id, ClientDTO dto) {
        try{
            clientService.updateClient(id, dto);
            return Response.status(Status.NO_CONTENT).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void deleteClient(@PathParam("id") Long id) {
        clientService.deleteClient(id);
    }
}