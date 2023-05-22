package com.livraria.eaglebookstore.service;

import java.util.List;

import com.livraria.eaglebookstore.dto.PedidoDTO;
import com.livraria.eaglebookstore.dto.PedidoResponseDTO;

public interface PedidoService {

    List<PedidoResponseDTO> listarPedidos();

    PedidoResponseDTO cadastrarPedido(PedidoDTO pedidoDTO);

    PedidoResponseDTO atualizarPedido(Long id, PedidoDTO pedidoDTO);

    PedidoResponseDTO buscarPedidoPorId(Long id);

    void excluirPedido(Long id);

}
