package com.livraria.eaglebookstore.service;

import java.util.List;

import com.livraria.eaglebookstore.dto.ItemPedidoDTO;
import com.livraria.eaglebookstore.dto.ItemPedidoResponseDTO;

public interface ItemPedidoService {

    List<ItemPedidoResponseDTO> listarItensPedido();

    ItemPedidoResponseDTO buscarItemPedidoPorId(Long id);

    ItemPedidoResponseDTO cadastrarItemPedido(ItemPedidoDTO itemPedidoDTO);

    ItemPedidoResponseDTO atualizarItemPedido(Long id, ItemPedidoDTO itemPedidoDTO);

    void excluirItemPedido(Long id);
}
