package com.livraria.eaglebookstore.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import com.livraria.eaglebookstore.dto.ItemPedidoDTO;
import com.livraria.eaglebookstore.dto.ItemPedidoResponseDTO;
import com.livraria.eaglebookstore.model.ItemPedido;

@ApplicationScoped
public interface ItemPedidoService {

    List<ItemPedidoResponseDTO> listarItensPedido();

    ItemPedido buscarItemPedidoPorId(Long id);

    ItemPedidoResponseDTO cadastrarItemPedido(ItemPedidoDTO dto);

    ItemPedido atualizarItemPedido(Long id, ItemPedidoDTO dto);

    void excluirItemPedido(Long id);
}
