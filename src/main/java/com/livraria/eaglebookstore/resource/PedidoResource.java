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

import com.livraria.eaglebookstore.model.Pedido;
import com.livraria.eaglebookstore.service.PedidoService;
import com.oracle.svm.core.annotate.Inject;

@Path("/pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResource {

    @Inject
    PedidoService pedidoService;

    @GET
    @Path("/{id}")
    public Pedido buscarPedidoPorId(@PathParam("id") Long id) {
        return pedidoService.buscarPedidoPorId(id);
    }

    @POST
    @Transactional
    public Pedido cadastrarPedido(@Valid Pedido pedido) {
        return pedidoService.cadastrarPedido(pedido);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Pedido atualizarPedido(@PathParam("id") Long id, @Valid Pedido pedido) {
        return pedidoService.atualizarPedido(id, pedido);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void excluirPedido(@PathParam("id") Long id) {
        pedidoService.excluirPedido(id);
    }
}

