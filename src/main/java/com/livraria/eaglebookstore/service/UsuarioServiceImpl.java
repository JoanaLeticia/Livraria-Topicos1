package com.livraria.eaglebookstore.service;

import java.util.List;
import java.util.stream.Collectors;

import com.livraria.eaglebookstore.dto.UsuarioResponseDTO;
import com.livraria.eaglebookstore.repository.UsuarioRepository;
import com.livraria.eaglebookstore.model.Usuario;
import com.oracle.svm.core.annotate.Inject;

import jakarta.transaction.Transactional;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

public class UsuarioServiceImpl implements UsuarioService {
    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    Validator validator;

    @Override
    public List<UsuarioResponseDTO> getAll() {
        List<Usuario> list = usuarioRepository.listAll();
        return list.stream().map(u -> UsuarioResponseDTO.valueOf(u)).collect(Collectors.toList());
    }

    @Override
    public UsuarioResponseDTO findById(Long id) {
        Usuario pessoaFisica = usuarioRepository.findById(id);
        
        if (pessoaFisica == null)
            throw new NotFoundException("Usuário não encontrado por nome.");
        return UsuarioResponseDTO.valueOf(pessoaFisica);
    }

    @Override
    public Usuario findByLoginAndSenha(String login, String senha) {
        return usuarioRepository.findByLoginAndSenha(login, senha);
    }

    @Override
    public UsuarioResponseDTO findByLogin(String login) {
        Usuario user = usuarioRepository.findByLogin(login);
        if (user == null)
            throw new NotFoundException("Usuário não encontrado por login.");
        return UsuarioResponseDTO.valueOf(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public List<UsuarioResponseDTO> findByNome(String nome) {
        List<Usuario> list = usuarioRepository.findByNome(nome);
        return list.stream().map(u -> UsuarioResponseDTO.valueOf(u)).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return usuarioRepository.count();
    }

}

    
