package com.livraria.eaglebookstore.service;

import java.util.List;

import com.livraria.eaglebookstore.dto.EnderecoDTO;
import com.livraria.eaglebookstore.dto.EnderecoResponseDTO;

public interface EnderecoService {

    List<EnderecoResponseDTO> listarEnderecos();

    EnderecoResponseDTO buscarEnderecoPorId(Long id);

    EnderecoResponseDTO cadastrarEndereco(EnderecoDTO enderecoDTO);

    EnderecoResponseDTO atualizarEndereco(Long id, EnderecoDTO enderecoDTO);

    void excluirEndereco(Long id);

}
