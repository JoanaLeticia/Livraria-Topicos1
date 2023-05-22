package com.livraria.eaglebookstore.repository;

import jakarta.enterprise.context.ApplicationScoped;

import com.livraria.eaglebookstore.model.Pedido;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class PedidoRepository implements PanacheRepository<Pedido> {

}
