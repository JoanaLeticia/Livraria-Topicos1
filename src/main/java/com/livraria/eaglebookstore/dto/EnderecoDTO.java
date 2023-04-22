package com.livraria.eaglebookstore.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EnderecoDTO {
    private boolean principal;

    @NotBlank(message = "Informe o logradouro.")
    private String logradouro;

    @NotBlank(message = "Informe o número.")
    private String numero;
    
    private String complemento;

    @NotBlank(message = "Informe o bairro.")
    private String bairro;

    @NotBlank(message = "Informe o CEP.")
    @Size(max = 8)
    private String cep;

    private Long municipioId;

    private Long clienteId;

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Long getMunicipioId() {
        return municipioId;
    }

    public void setMunicipioId(Long municipioId) {
        this.municipioId = municipioId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }
    
}
