package com.livraria.eaglebookstore.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.livraria.eaglebookstore.model.Cliente;
import com.livraria.eaglebookstore.model.Pedido;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class PedidoRepository implements PanacheRepository<Pedido> {

    public Pedido findById(Long id) {
        return this.findById(id);
    }

    public void persist(Pedido pedido) {
        this.persist(pedido);
    }

    public void update(Pedido pedido) {
        this.update(pedido);
    }

    public void delete(Long id) {
        this.deleteById(id);
    }

    public List<Pedido> findByCliente(Cliente cliente) {
        return this.find("cliente", cliente).list();
    }

    public Pedido buscarPedidoPorId(Long id) {
        return null;
    }

}
