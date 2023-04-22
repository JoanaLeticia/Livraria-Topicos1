package com.livraria.eaglebookstore.service;

import java.util.Date;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.livraria.eaglebookstore.model.ItemPedido;
import com.livraria.eaglebookstore.model.Pedido;
import com.livraria.eaglebookstore.model.StatusPedido;
import com.livraria.eaglebookstore.repository.PedidoRepository;

@ApplicationScoped
public class PedidoService {

    @Inject
    PedidoRepository pedidoRepository;

    @Transactional
    public Pedido cadastrarPedido(Pedido pedido) {
        pedido.setData(new Date());
        pedido.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);
        calcularQuantidadeEValorTotal(pedido);
        pedidoRepository.persist(pedido);
        return pedido;
    }

    @Transactional
    public Pedido atualizarPedido(Long id, Pedido pedidoAtualizado) {
        Pedido pedido = buscarPedidoPorId(id);
        pedidoAtualizado.setId(pedido.getId());
        calcularQuantidadeEValorTotal(pedidoAtualizado);
        pedidoRepository.getEntityManager().merge(pedidoAtualizado);
        return pedidoAtualizado;
    }

    public Pedido buscarPedidoPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    private void calcularQuantidadeEValorTotal(Pedido pedido) {
        int quantidadeTotal = 0;
        double valorTotal = 0.0;

        for (ItemPedido item : pedido.getItensPedido()) {
            quantidadeTotal += item.getQuantidade();
            valorTotal += item.getPreco() * item.getQuantidade();
        }

        pedido.setQuantidadeTotal(quantidadeTotal);
        pedido.setValorTotal(valorTotal);
    }

    @Transactional
        public void excluirPedido(Long id) {
        pedidoRepository.deleteById(id);
    }

}
