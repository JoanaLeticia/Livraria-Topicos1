package com.livraria.eaglebookstore.resource;

import java.util.List;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.inject.Inject;

import com.livraria.eaglebookstore.application.Result;
import com.livraria.eaglebookstore.dto.PedidoDTO;
import com.livraria.eaglebookstore.dto.PedidoResponseDTO;
import com.livraria.eaglebookstore.service.PedidoService;

@Path("/pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResource {

    @Inject
    PedidoService pedidoService;

    public PedidoResource(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GET
    public List<PedidoResponseDTO> listarPedidos() {
        return pedidoService.listarPedidos();
    }

    @GET
    @Path("/{id}")
    public PedidoResponseDTO buscarPedidoPorId(@PathParam("id") Long id) {
        return pedidoService.buscarPedidoPorId(id);
    }

    @POST
    public Response cadastrarPedido(PedidoDTO dto) {
        try {
            PedidoResponseDTO pedido = pedidoService.cadastrarPedido(dto);
            return Response.status(Status.CREATED).entity(pedido).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarPedido(@PathParam("id") Long id, PedidoDTO dto) {
        try {
            pedidoService.atualizarPedido(id, dto);
            return Response.status(Status.NO_CONTENT).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }      
    }

    @DELETE
    @Path("/{id}")
    public Response excluirPedido(@PathParam("id") Long id) {
        pedidoService.excluirPedido(id);
        return Response.status(Status.NO_CONTENT).build();
    }
}

