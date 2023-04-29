package com.livraria.eaglebookstore.service;

import com.livraria.eaglebookstore.dto.ClienteDTO;
import com.livraria.eaglebookstore.dto.ClienteResponseDTO;
import com.livraria.eaglebookstore.model.Cliente;
import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public interface ClienteService {

    List<Cliente> listarClientes();

    Cliente buscarClientePorId(Long id);

    Cliente cadastrarCliente(Cliente cliente);

    ClienteResponseDTO atualizarCliente(Long id, ClienteDTO cliente);

    void excluirCliente(Long id);
}