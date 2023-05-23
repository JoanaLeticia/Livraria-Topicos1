package com.bookstore.eagle.service;

import java.util.List;

import com.bookstore.eagle.dto.ClientDTO;
import com.bookstore.eagle.dto.ClientResponseDTO;

public interface ClientService {

    List<ClientResponseDTO> listClients();

    ClientResponseDTO searchClientById(Long id);

    ClientResponseDTO addClient(ClientDTO clientDTO);

    ClientResponseDTO updateClient(Long id, ClientDTO clientDTO);

    void deleteClient(Long id);
}