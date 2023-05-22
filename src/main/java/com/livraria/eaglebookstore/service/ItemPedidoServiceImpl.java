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

import com.livraria.eaglebookstore.dto.ItemPedidoDTO;
import com.livraria.eaglebookstore.dto.ItemPedidoResponseDTO;
import com.livraria.eaglebookstore.model.ItemPedido;
import com.livraria.eaglebookstore.repository.ItemPedidoRepository;

@ApplicationScoped
public class ItemPedidoServiceImpl implements ItemPedidoService {
    @Inject
    ItemPedidoRepository itemPedidoRepository;

    @Inject
    Validator validator;

    @Override
    public List<ItemPedidoResponseDTO> listarItensPedido() {
        List<ItemPedido> list = itemPedidoRepository.listAll();
        return list.stream().map(ItemPedidoResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public ItemPedidoResponseDTO buscarItemPedidoPorId(Long id) {
        ItemPedido itemPedido = itemPedidoRepository.findById(id);
        if (itemPedido == null)
            throw new NotFoundException("O item pedido não foi encontrado.");
        return new ItemPedidoResponseDTO(itemPedido);
    }

    @Override
    @Transactional
    public ItemPedidoResponseDTO cadastrarItemPedido(ItemPedidoDTO itemPedidoDTO) throws ConstraintViolationException {
        validar(itemPedidoDTO);

        ItemPedido entity = new ItemPedido();
        entity.setQuantidade(itemPedidoDTO.quantidade());
        entity.setPreco(itemPedidoDTO.preco());
        itemPedidoRepository.persist(entity);

        return new ItemPedidoResponseDTO(entity);

    }

    @Override
    @Transactional
    public ItemPedidoResponseDTO atualizarItemPedido(Long id, ItemPedidoDTO itemPedidoDTO) throws ConstraintViolationException {
        validar(itemPedidoDTO);

        ItemPedido entity = itemPedidoRepository.findById(id);

        entity.setQuantidade(itemPedidoDTO.quantidade());
        entity.setPreco(itemPedidoDTO.preco());
        
        return new ItemPedidoResponseDTO(entity);
    }

    private void validar(ItemPedidoDTO itemPedidoDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<ItemPedidoDTO>> violations = validator.validate(itemPedidoDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

    @Override
    @Transactional
    public void excluirItemPedido(Long id) {
        itemPedidoRepository.deleteById(id);
    }

}
