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

import com.livraria.eaglebookstore.dto.EnderecoDTO;
import com.livraria.eaglebookstore.dto.EnderecoResponseDTO;
import com.livraria.eaglebookstore.model.Endereco;
import com.livraria.eaglebookstore.repository.EnderecoRepository;

@ApplicationScoped
public class EnderecoServiceImpl implements EnderecoService {
    @Inject
    EnderecoRepository enderecoRepository;

    @Inject
    Validator validator;

    @Override
    public List<EnderecoResponseDTO> listarEnderecos() {
        List<Endereco> list = enderecoRepository.listAll();
        return list.stream().map(EnderecoResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public EnderecoResponseDTO buscarEnderecoPorId(Long id) {
        Endereco endereco = enderecoRepository.findById(id);
        if (endereco == null)
            throw new NotFoundException("O endereço não foi encontrado.");
        return new EnderecoResponseDTO(endereco);
    }

    @Override
    @Transactional
    public EnderecoResponseDTO cadastrarEndereco(EnderecoDTO enderecoDTO) throws ConstraintViolationException {
        validar(enderecoDTO);

        Endereco entity = new Endereco();
        entity.setPrincipal(enderecoDTO.principal());
        entity.setLogradouro(enderecoDTO.logradouro());
        entity.setNumero(enderecoDTO.numero());
        entity.setComplemento(enderecoDTO.complemento());
        entity.setBairro(enderecoDTO.bairro());
        entity.setCep(enderecoDTO.cep());
        enderecoRepository.persist(entity);

        return new EnderecoResponseDTO(entity);

    }

    @Override
    @Transactional
    public EnderecoResponseDTO atualizarEndereco(Long id, EnderecoDTO enderecoDTO) throws ConstraintViolationException {
        validar(enderecoDTO);

        Endereco entity = enderecoRepository.findById(id);

        entity.setPrincipal(enderecoDTO.principal());
        entity.setLogradouro(enderecoDTO.logradouro());
        entity.setNumero(enderecoDTO.numero());
        entity.setComplemento(enderecoDTO.complemento());
        entity.setBairro(enderecoDTO.bairro());
        entity.setCep(enderecoDTO.cep());
        
        return new EnderecoResponseDTO(entity);
    }

    private void validar(EnderecoDTO enderecoDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<EnderecoDTO>> violations = validator.validate(enderecoDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

    @Override
    @Transactional
    public void excluirEndereco(Long id) {
        enderecoRepository.deleteById(id);
    }

}
