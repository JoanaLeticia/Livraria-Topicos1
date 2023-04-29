package com.livraria.eaglebookstore.dto;

import java.util.List;

public class ClienteResponseDTO {

    private Long id;
    private String email;
    private List<String> telefones;
    private List<EnderecoDTO> enderecos;

    public ClienteResponseDTO() {
    }

    public ClienteResponseDTO(Long id, String email, List<String> telefones, List<EnderecoDTO> enderecos) {
        this.id = id;
        this.email = email;
        this.telefones = telefones;
        this.enderecos = enderecos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<String> telefones) {
        this.telefones = telefones;
    }

    public List<EnderecoDTO> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecoDTO> enderecos) {
        this.enderecos = enderecos;
    }
}
