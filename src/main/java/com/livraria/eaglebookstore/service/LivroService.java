package com.livraria.eaglebookstore.service;

import com.livraria.eaglebookstore.dto.LivroDTO;
import com.livraria.eaglebookstore.dto.LivroResponseDTO;
import com.livraria.eaglebookstore.model.Livro;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface LivroService {

    LivroResponseDTO cadastrarLivro(LivroDTO dto);

    LivroResponseDTO buscarLivroPorId(Long id);

    LivroResponseDTO atualizarLivro(Long id, LivroDTO dto);

    void excluirLivro(Long id);

    List<Livro> listarLivros();

}
