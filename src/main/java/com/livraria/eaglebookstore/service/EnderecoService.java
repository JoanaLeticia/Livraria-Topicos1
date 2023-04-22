package com.livraria.eaglebookstore.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import com.livraria.eaglebookstore.model.Endereco;
import com.livraria.eaglebookstore.repository.EnderecoRepository;

@ApplicationScoped
public class EnderecoService {

    @Inject
    EnderecoRepository enderecoRepository;

    public List<Endereco> listarEnderecos() {
        return enderecoRepository.listAll();
    }

    public Endereco buscarEnderecoPorId(Long id) {
        return enderecoRepository.findById(id);
    }

    @Transactional
    public Endereco cadastrarEndereco(@Valid Endereco endereco) {
        if (endereco.isPrincipal()) {
            limparEnderecosPrincipaisDoCliente(endereco.getCliente().getId());
        }
        enderecoRepository.persist(endereco);
        return endereco;
    }

    @Transactional
        public void atualizarEndereco(Long id, Endereco enderecoAtualizado) {
        Endereco endereco = buscarEnderecoPorId(id);
        enderecoAtualizado.setId(endereco.getId());
        enderecoAtualizado.setCliente(endereco.getCliente());
        enderecoRepository.persist(enderecoAtualizado);
    }



    @Transactional
    public void excluirEndereco(Long id) {
        Endereco endereco = buscarEnderecoPorId(id);
        if (endereco == null) {
            throw new WebApplicationException("Endereço não encontrado", Status.NOT_FOUND);
        }
        enderecoRepository.delete(endereco);
    }

    private void limparEnderecosPrincipaisDoCliente(Long clienteId) {
        List<Endereco> enderecos = enderecoRepository.findByClienteId(clienteId);
        enderecos.forEach(e -> e.setPrincipal(false));
        enderecoRepository.persist(enderecos);
    }

}
