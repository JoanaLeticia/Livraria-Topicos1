package com.livraria.eaglebookstore.converterjpa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import com.livraria.eaglebookstore.model.StatusPedido;

@Converter(autoApply = true)
public class StatusPedidoConverter implements AttributeConverter<StatusPedido, Integer> {
    
    @Override
    public Integer convertToDatabaseColumn(StatusPedido statusPedido) {
        return statusPedido == null ? null : statusPedido.getId();
    }

    @Override
    public StatusPedido convertToEntityAttribute(Integer id) {
        return StatusPedido.valueOf(id);
    }
}