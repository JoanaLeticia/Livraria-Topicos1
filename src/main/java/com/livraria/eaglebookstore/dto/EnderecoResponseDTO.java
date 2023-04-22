package com.livraria.eaglebookstore.dto;

public class EnderecoResponseDTO {

    private Long id;
    private boolean principal;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    private MunicipioResponseDTO municipio;

    public EnderecoResponseDTO(Long id, boolean principal, String logradouro, String numero, String complemento,
            String bairro, String cep, MunicipioResponseDTO municipio) {
        this.id = id;
        this.principal = principal;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.municipio = municipio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public MunicipioResponseDTO getMunicipio() {
        return municipio;
    }

    public void setMunicipio(MunicipioResponseDTO municipio) {
        this.municipio = municipio;
    }

}
