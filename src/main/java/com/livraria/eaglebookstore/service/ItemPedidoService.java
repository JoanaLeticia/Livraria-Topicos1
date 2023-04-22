package com.livraria.eaglebookstore.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.livraria.eaglebookstore.model.ItemPedido;
import com.livraria.eaglebookstore.repository.ItemPedidoRepository;

@ApplicationScoped
public class ItemPedidoService {

    @Inject
    ItemPedidoRepository itemPedidoRepository;

    public List<ItemPedido> listarItensPedido() {
        return itemPedidoRepository.listAll();
    }

    public ItemPedido buscarItemPedidoPorId(Long id) {
        return itemPedidoRepository.findById(id);
    }

    @Transactional
    public ItemPedido cadastrarItemPedido(ItemPedido itemPedido) {
        return itemPedidoRepository.save(itemPedido);
    }

    @Transactional
    public ItemPedido atualizarItemPedido(Long id, ItemPedido itemPedidoAtualizado) {
        ItemPedido itemPedido = buscarItemPedidoPorId(id);
        itemPedido.setQuantidade(itemPedidoAtualizado.getQuantidade());
        itemPedido.setLivro(itemPedidoAtualizado.getLivro());
        itemPedido.setPedido(itemPedidoAtualizado.getPedido());
        return itemPedidoRepository.save(itemPedido);
    }

    @Transactional
    public void excluirItemPedido(Long id) {
        itemPedidoRepository.deleteById(id);
    }
}
