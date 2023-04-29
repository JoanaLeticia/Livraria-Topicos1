package com.livraria.eaglebookstore.service;

import com.livraria.eaglebookstore.dto.LivroDTO;
import com.livraria.eaglebookstore.dto.LivroResponseDTO;
import java.util.List;

public interface LivroService {

    LivroResponseDTO cadastrarLivro(LivroDTO dto);

    LivroResponseDTO buscarLivroPorId(Long id);

    LivroResponseDTO atualizarLivro(Long id, LivroDTO dto);

    void excluirLivro(Long id);

    List<LivroResponseDTO> listarLivros();

}
