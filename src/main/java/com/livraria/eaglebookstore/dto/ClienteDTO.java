package com.livraria.eaglebookstore.dto;

import java.util.List;

import com.livraria.eaglebookstore.model.Endereco;
import com.livraria.eaglebookstore.model.Pedido;
import com.livraria.eaglebookstore.model.Produto;
import com.livraria.eaglebookstore.model.Telefone;

public class ClienteDTO {

    private Long id;
    private String email;
    private List<Telefone> telefones;
    private List<Endereco> enderecos;
    private List<Pedido> pedidos;
    private List<Produto> listaDesejos;

    public ClienteDTO() {
    }

    public ClienteDTO(Long id, String email, List<Telefone> telefones, List<Endereco> enderecos, List<Pedido> pedidos, List<Produto> listaDesejos) {
        this.id = id;
        this.email = email;
        this.telefones = telefones;
        this.enderecos = enderecos;
        this.pedidos = pedidos;
        this.listaDesejos = listaDesejos;
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

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<Produto> getListaDesejos() {
        return listaDesejos;
    }

    public void setListaDesejos(List<Produto> listaDesejos) {
        this.listaDesejos = listaDesejos;
    }
}
