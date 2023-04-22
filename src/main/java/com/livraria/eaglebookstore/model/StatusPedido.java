package com.livraria.eaglebookstore.model;

public enum StatusPedido {
    AGUARDANDO_PAGAMENTO(1, "Aguardando Pagamento"),
    PROCESSANDO(2, "Processando"),
    ENVIADO(3, "Enviado"),
    ENTREGUE(4, "Entregue"),
    CANCELADO(5, "Cancelado");

    private int id;

    private String label;
    
    private StatusPedido(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static StatusPedido valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (StatusPedido statusPedido : StatusPedido.values()) {
            if (id.equals(statusPedido.getId()))
                return statusPedido;
        }
        throw new IllegalArgumentException("Esse id é inválido:" + id);
    }
}
