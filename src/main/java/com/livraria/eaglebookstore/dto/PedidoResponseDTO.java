package com.livraria.eaglebookstore.dto;

import java.util.Date;

import com.livraria.eaglebookstore.model.StatusPedido;

public class PedidoResponseDTO {
    private Long id;
    private Integer quantidadeTotal;
    private Double valorTotal;
    private Date data;
    private StatusPedido status;

    public PedidoResponseDTO(Long id, Integer quantidadeTotal, Double valorTotal, Date data, StatusPedido status) {
        this.id = id;
        this.quantidadeTotal = quantidadeTotal;
        this.valorTotal = valorTotal;
        this.data = data;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantidadeTotal() {
        return quantidadeTotal;
    }

    public void setQuantidadeTotal(Integer quantidadeTotal) {
        this.quantidadeTotal = quantidadeTotal;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

}
