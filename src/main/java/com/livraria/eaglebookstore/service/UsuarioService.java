package com.livraria.eaglebookstore.service;

import java.util.List;

import com.livraria.eaglebookstore.dto.UsuarioResponseDTO;
import com.livraria.eaglebookstore.model.Usuario;

public interface UsuarioService {
    List<UsuarioResponseDTO> getAll();

    UsuarioResponseDTO findById(Long id);

    Usuario findByLoginAndSenha(String login, String senha);

    UsuarioResponseDTO findByLogin(String login);

    void delete(Long id);

    List<UsuarioResponseDTO> findByNome(String nome);

    long count();
}
