package com.livraria.eaglebookstore.resource;

import java.util.List;

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
import com.livraria.eaglebookstore.dto.PedidoDTO;
import com.livraria.eaglebookstore.dto.PedidoResponseDTO;
import com.livraria.eaglebookstore.service.PedidoService;
import com.oracle.svm.core.annotate.Inject;

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

