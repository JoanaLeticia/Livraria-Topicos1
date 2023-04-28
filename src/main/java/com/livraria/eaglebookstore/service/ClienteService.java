package com.livraria.eaglebookstore.service;

import com.livraria.eaglebookstore.model.Cliente;
import com.livraria.eaglebookstore.repository.ClienteRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ClienteService {

    @Inject
    ClienteRepository clienteRepository;

    public List<Cliente> listarClientes() {
        return clienteRepository.listAll();
    }

    public Cliente buscarClientePorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente cadastrarCliente(Cliente cliente) {
        clienteRepository.persist(cliente);
        return cliente;
    }

    @Transactional
    public Cliente atualizarCliente(Long id, Cliente clienteAtualizado) {
        Cliente cliente = buscarClientePorId(id);
        clienteAtualizado.setId(cliente.getId());
        clienteRepository.persist(clienteAtualizado);
        return clienteAtualizado;
    }

    @Transactional
    public void excluirCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}