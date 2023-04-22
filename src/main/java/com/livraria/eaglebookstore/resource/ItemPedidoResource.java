package com.livraria.eaglebookstore.resource;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.livraria.eaglebookstore.model.ItemPedido;
import com.livraria.eaglebookstore.service.ItemPedidoService;

@Path("/itenspedido")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemPedidoResource {

    @Inject
    ItemPedidoService itemPedidoService;

    @GET
    @Path("/{id}")
    public ItemPedido buscarItemPedidoPorId(@PathParam("id") Long id) {
        return itemPedidoService.buscarItemPedidoPorId(id);
    }

    @POST
    @Transactional
    public ItemPedido cadastrarItemPedido(ItemPedido itemPedido) {
        return itemPedidoService.cadastrarItemPedido(itemPedido);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public ItemPedido atualizarItemPedido(@PathParam("id") Long id, ItemPedido itemPedidoAtualizado) {
        return itemPedidoService.atualizarItemPedido(id, itemPedidoAtualizado);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void excluirItemPedido(@PathParam("id") Long id) {
        itemPedidoService.excluirItemPedido(id);
    }
}
