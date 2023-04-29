package com.livraria.eaglebookstore.service;

import com.livraria.eaglebookstore.dto.ClienteDTO;
import com.livraria.eaglebookstore.dto.ClienteResponseDTO;
import java.util.List;

public interface ClienteService {

    List<ClienteResponseDTO> listarClientes();

    ClienteResponseDTO buscarClientePorId(Long id);

    ClienteResponseDTO cadastrarCliente(ClienteDTO dto);

    ClienteResponseDTO atualizarCliente(Long id, ClienteDTO cliente);

    void excluirCliente(Long id);
}