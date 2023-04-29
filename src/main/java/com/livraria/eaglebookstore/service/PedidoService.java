package com.livraria.eaglebookstore.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.livraria.eaglebookstore.dto.PedidoDTO;
import com.livraria.eaglebookstore.dto.PedidoResponseDTO;
import com.livraria.eaglebookstore.model.Pedido;

@ApplicationScoped
public interface PedidoService {

    PedidoResponseDTO cadastrarPedido(PedidoDTO dto);

    Pedido atualizarPedido(Long id, PedidoDTO dto);

    Pedido buscarPedidoPorId(Long id);

    void calcularQuantidadeEValorTotal(Pedido pedido);

    void excluirPedido(Long id);

    List<PedidoResponseDTO> listarPedidos();

}
