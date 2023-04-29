package com.livraria.eaglebookstore.service;

import java.util.List;

import com.livraria.eaglebookstore.dto.PedidoDTO;
import com.livraria.eaglebookstore.dto.PedidoResponseDTO;

public interface PedidoService {

    PedidoResponseDTO cadastrarPedido(PedidoDTO dto);

    PedidoResponseDTO atualizarPedido(Long id, PedidoDTO dto);

    PedidoResponseDTO buscarPedidoPorId(Long id);

    void excluirPedido(Long id);

    List<PedidoResponseDTO> listarPedidos();

}
