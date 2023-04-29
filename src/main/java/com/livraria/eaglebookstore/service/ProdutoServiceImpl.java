package com.livraria.eaglebookstore.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.ws.rs.NotFoundException;

import com.livraria.eaglebookstore.dto.ProdutoDTO;
import com.livraria.eaglebookstore.dto.ProdutoResponseDTO;
import com.livraria.eaglebookstore.model.Produto;
import com.livraria.eaglebookstore.repository.ProdutoRepository;
import com.oracle.svm.core.annotate.Inject;

public class ProdutoServiceImpl implements ProdutoService {
    @Inject
    ProdutoRepository produtoRepository;

    @Inject
    Validator validator;

    @Override
    public List<ProdutoResponseDTO> listarProdutos() {
        List<Produto> list = produtoRepository.listAll();
        return list.stream().map(ProdutoResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public ProdutoResponseDTO buscarProdutoPorId(Long id) {
        Produto produto = produtoRepository.findById(id);
        if (produto == null)
            throw new NotFoundException("O produto não foi encontrado.");
        return new ProdutoResponseDTO(produto);
    }

    @Override
    @Transactional
    public ProdutoResponseDTO cadastrarProduto(ProdutoDTO produtoDTO) throws ConstraintViolationException {
        validar(produtoDTO);

        Produto entity = new Produto();
        entity.setNome(produtoDTO.nome());
        entity.setDescricao(produtoDTO.descricao());
        entity.setPreco(produtoDTO.preco());
        entity.setEstoque(produtoDTO.estoque());
        produtoRepository.persist(entity);

        return new ProdutoResponseDTO(entity);

    }

    @Override
    @Transactional
    public ProdutoResponseDTO atualizarProduto(Long id, ProdutoDTO produtoDTO) throws ConstraintViolationException {
        validar(produtoDTO);

        Produto entity = produtoRepository.findById(id);

        entity.setNome(produtoDTO.nome());
        entity.setDescricao(produtoDTO.descricao());
        entity.setPreco(produtoDTO.preco());
        entity.setEstoque(produtoDTO.estoque());
        
        return new ProdutoResponseDTO(entity);
    }

    private void validar(ProdutoDTO produtoDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<ProdutoDTO>> violations = validator.validate(produtoDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

    @Override
    @Transactional
    public void excluirProduto(Long id) {
        produtoRepository.deleteById(id);
    }

}
