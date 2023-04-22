package com.livraria.eaglebookstore.repository;

import javax.enterprise.context.ApplicationScoped;

import com.livraria.eaglebookstore.model.ItemPedido;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class ItemPedidoRepository implements PanacheRepository<ItemPedido> {

    public ItemPedido save(ItemPedido itemPedido) {
        return null;
    }

}
