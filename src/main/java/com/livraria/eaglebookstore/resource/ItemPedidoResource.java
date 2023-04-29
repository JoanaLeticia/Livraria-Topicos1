package com.livraria.eaglebookstore.resource;

import java.util.List;

import javax.inject.Inject;
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
import com.livraria.eaglebookstore.dto.ItemPedidoDTO;
import com.livraria.eaglebookstore.dto.ItemPedidoResponseDTO;
import com.livraria.eaglebookstore.service.ItemPedidoService;

@Path("/itenspedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemPedidoResource {

    @Inject
    ItemPedidoService itemPedidoService;

    @GET
    public List<ItemPedidoResponseDTO> listarItensPedido() {
        return itemPedidoService.listarItensPedido();
    }

    @GET
    @Path("/{id}")
    public ItemPedidoResponseDTO buscarItemPedidoPorId(@PathParam("id") Long id) {
        return itemPedidoService.buscarItemPedidoPorId(id);
    }

    @POST
    public Response cadastrarItemPedido(ItemPedidoDTO dto) {
        try {
            ItemPedidoResponseDTO itemPedido = itemPedidoService.cadastrarItemPedido(dto);
            return Response.status(Status.CREATED).entity(itemPedido).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarItemPedido(@PathParam("id") Long id, ItemPedidoDTO dto) {
        try {
            itemPedidoService.atualizarItemPedido(id, dto);
            return Response.status(Status.NO_CONTENT).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }      
    }

    @DELETE
    @Path("/{id}")
    public Response excluirItemPedido(@PathParam("id") Long id) {
        itemPedidoService.excluirItemPedido(id);
        return Response.status(Status.NO_CONTENT).build();
    }
}
