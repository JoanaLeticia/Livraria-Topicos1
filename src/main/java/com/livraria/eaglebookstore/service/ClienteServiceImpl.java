package com.livraria.eaglebookstore.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

import com.livraria.eaglebookstore.dto.ClienteDTO;
import com.livraria.eaglebookstore.dto.ClienteResponseDTO;
import com.livraria.eaglebookstore.model.Cliente;
import com.livraria.eaglebookstore.repository.ClienteRepository;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteService {

    @Inject
    ClienteRepository clienteRepository;

    @Inject
    Validator validator;

    @Override
    public List<ClienteResponseDTO> listarClientes() {
        List<Cliente> list = clienteRepository.listAll();
        return list.stream().map(ClienteResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public ClienteResponseDTO buscarClientePorId(Long id) {
        Cliente cliente = clienteRepository.findById(id);
        if (cliente == null)
            throw new NotFoundException("O cliente não foi encontrado.");
        return new ClienteResponseDTO(cliente);
    }

    @Override
    @Transactional
    public ClienteResponseDTO cadastrarCliente(ClienteDTO clienteDTO) throws ConstraintViolationException {
        validar(clienteDTO);

        Cliente entity = new Cliente();
        entity.setEmail(clienteDTO.email());
        entity.setPedidos(clienteDTO.pedidos());
        entity.setListaDesejos(clienteDTO.listaDesejos());
        clienteRepository.persist(entity);

        return new ClienteResponseDTO(entity);

    }

    @Override
    @Transactional
    public ClienteResponseDTO atualizarCliente(Long id, ClienteDTO clienteDTO) throws ConstraintViolationException {
        validar(clienteDTO);

        Cliente entity = clienteRepository.findById(id);

        entity.setEmail(clienteDTO.email());
        entity.setPedidos(clienteDTO.pedidos());
        entity.setListaDesejos(clienteDTO.listaDesejos());
        
        return new ClienteResponseDTO(entity);
    }

    private void validar(ClienteDTO clienteDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<ClienteDTO>> violations = validator.validate(clienteDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

    @Override
    @Transactional
    public void excluirCliente(Long id) {
        clienteRepository.deleteById(id);
    }

}
