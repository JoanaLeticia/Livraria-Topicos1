package com.livraria.eaglebookstore.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.ws.rs.NotFoundException;

import com.livraria.eaglebookstore.dto.LivroDTO;
import com.livraria.eaglebookstore.dto.LivroResponseDTO;
import com.livraria.eaglebookstore.model.ClassificacaoEtaria;
import com.livraria.eaglebookstore.model.Genero;
import com.livraria.eaglebookstore.model.Livro;
import com.livraria.eaglebookstore.repository.LivroRepository;
import com.oracle.svm.core.annotate.Inject;

public class LivroServiceImpl implements LivroService {
    @Inject
    LivroRepository livroRepository;

    @Inject
    Validator validator;

    @Override
    public List<LivroResponseDTO> listarLivros() {
        List<Livro> list = livroRepository.listAll();
        return list.stream().map(LivroResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public LivroResponseDTO buscarLivroPorId(Long id) {
        Livro livro = livroRepository.findById(id);
        if (livro == null)
            throw new NotFoundException("O livro não foi encontrado.");
        return new LivroResponseDTO(livro);
    }

    @Override
    @Transactional
    public LivroResponseDTO cadastrarLivro(LivroDTO livroDTO) throws ConstraintViolationException {
        validar(livroDTO);

        Livro entity = new Livro();
        entity.setNome(livroDTO.nome());
        entity.setDescricao(livroDTO.descricao());
        entity.setAutor(livroDTO.autor());
        entity.setEditora(livroDTO.editora());
        entity.setGenero(Genero.valueOf(livroDTO.genero()));
        entity.setClassificacaoEtaria(ClassificacaoEtaria.valueOf(livroDTO.classificacaoEtaria()));
        entity.setPreco(livroDTO.preco());
        entity.setEstoque(livroDTO.estoque());
        livroRepository.persist(entity);

        return new LivroResponseDTO(entity);

    }

    @Override
    @Transactional
    public LivroResponseDTO atualizarLivro(Long id, LivroDTO livroDTO) throws ConstraintViolationException {
        validar(livroDTO);

        Livro entity = livroRepository.findById(id);

        entity.setNome(livroDTO.nome());
        entity.setDescricao(livroDTO.descricao());
        entity.setAutor(livroDTO.autor());
        entity.setEditora(livroDTO.editora());
        entity.setGenero(Genero.valueOf(livroDTO.genero()));
        entity.setClassificacaoEtaria(ClassificacaoEtaria.valueOf(livroDTO.classificacaoEtaria()));
        entity.setPreco(livroDTO.preco());
        entity.setEstoque(livroDTO.estoque());
        
        return new LivroResponseDTO(entity);
    }

    private void validar(LivroDTO livroDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<LivroDTO>> violations = validator.validate(livroDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

    @Override
    @Transactional
    public void excluirLivro(Long id) {
        livroRepository.deleteById(id);
    }

}
