package com.livraria.eaglebookstore.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

import com.livraria.eaglebookstore.dto.PedidoDTO;
import com.livraria.eaglebookstore.dto.PedidoResponseDTO;
import com.livraria.eaglebookstore.model.Pedido;
import com.livraria.eaglebookstore.model.StatusPedido;
import com.livraria.eaglebookstore.repository.PedidoRepository;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService {
    @Inject
    PedidoRepository pedidoRepository;

    @Inject
    Validator validator;

    @Override
    public List<PedidoResponseDTO> listarPedidos() {
        List<Pedido> list = pedidoRepository.listAll();
        return list.stream().map(PedidoResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public PedidoResponseDTO buscarPedidoPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id);
        if (pedido == null)
            throw new NotFoundException("O pedido não foi encontrado.");
        return new PedidoResponseDTO(pedido);
    }

    @Override
    @Transactional
    public PedidoResponseDTO cadastrarPedido(PedidoDTO pedidoDTO) throws ConstraintViolationException {
        validar(pedidoDTO);

        Pedido entity = new Pedido();
        entity.setQuantidadeTotal(pedidoDTO.quantidadeTotal());
        entity.setValorTotal(pedidoDTO.valorTotal());
        entity.setStatus(StatusPedido.valueOf(pedidoDTO.status()));
        pedidoRepository.persist(entity);

        return new PedidoResponseDTO(entity);

    }

    @Override
    @Transactional
    public PedidoResponseDTO atualizarPedido(Long id, PedidoDTO pedidoDTO) throws ConstraintViolationException {
        validar(pedidoDTO);

        Pedido entity = pedidoRepository.findById(id);

        entity.setQuantidadeTotal(pedidoDTO.quantidadeTotal());
        entity.setValorTotal(pedidoDTO.valorTotal());
        entity.setStatus(StatusPedido.valueOf(pedidoDTO.status()));
        
        return new PedidoResponseDTO(entity);
    }

    private void validar(PedidoDTO pedidoDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<PedidoDTO>> violations = validator.validate(pedidoDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

    @Override
    @Transactional
    public void excluirPedido(Long id) {
        pedidoRepository.deleteById(id);
    }

}
