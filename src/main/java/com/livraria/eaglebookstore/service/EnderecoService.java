package com.livraria.eaglebookstore.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import com.livraria.eaglebookstore.dto.EnderecoDTO;
import com.livraria.eaglebookstore.dto.EnderecoResponseDTO;

@ApplicationScoped
public interface EnderecoService {

    List<EnderecoResponseDTO> listarEnderecos();

    EnderecoResponseDTO buscarEnderecoPorId(Long id);

    EnderecoResponseDTO cadastrarEndereco(EnderecoDTO dto);

    void atualizarEndereco(Long id, EnderecoDTO dto);

    void excluirEndereco(Long id);

    void limparEnderecosPrincipaisDoCliente(Long clienteId);

}
