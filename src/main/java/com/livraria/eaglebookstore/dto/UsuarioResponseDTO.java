package com.livraria.eaglebookstore.dto;

import com.livraria.eaglebookstore.model.Usuario;

public record UsuarioResponseDTO (
    Long id,
    String nome,
    String email,
    String login
) {

    public static UsuarioResponseDTO valueOf(Usuario u) {
        if (u.getPessoaFisica() == null)
            return new UsuarioResponseDTO(u.getId(), null, null, u.getLogin());
        
            return new UsuarioResponseDTO(u.getId(),
                u.getPessoaFisica().getNome(),
                u.getCliente().getEmail(),
                u.getLogin());
    }

}
