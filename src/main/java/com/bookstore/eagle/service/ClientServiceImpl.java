package com.bookstore.eagle.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.bookstore.eagle.dto.ClientDTO;
import com.bookstore.eagle.dto.ClientResponseDTO;
import com.bookstore.eagle.model.Client;
import com.bookstore.eagle.repository.ClientRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class ClientServiceImpl implements ClientService {

    @Inject
    ClientRepository clientRepository;

    @Inject
    Validator validator;

    @Override
    public List<ClientResponseDTO> listClients() {
        List<Client> list = clientRepository.listAll();
        return list.stream().map(ClientResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public ClientResponseDTO searchClientById(Long id) {
        Client client = clientRepository.findById(id);
        if (client == null)
            throw new NotFoundException("The client was not found.");
        return new ClientResponseDTO(client);
    }

    @Override
    @Transactional
    public ClientResponseDTO addClient(ClientDTO clientDTO) throws ConstraintViolationException {
        validating(clientDTO);

        Client entity = new Client();
        entity.setEmail(clientDTO.email());
        entity.setOrders(clientDTO.orders());
        clientRepository.persist(entity);

        return new ClientResponseDTO(entity);

    }

    @Override
    @Transactional
    public ClientResponseDTO updateClient(Long id, ClientDTO clientDTO) throws ConstraintViolationException {
        validating(clientDTO);

        Client entity = clientRepository.findById(id);

        entity.setEmail(clientDTO.email());
        entity.setOrders(clientDTO.orders());
        
        return new ClientResponseDTO(entity);
    }

    private void validating(ClientDTO clientDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<ClientDTO>> violations = validator.validate(clientDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

    @Override
    @Transactional
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

}
