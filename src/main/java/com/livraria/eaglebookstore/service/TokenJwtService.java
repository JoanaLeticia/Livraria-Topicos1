package com.livraria.eaglebookstore.service;

import com.livraria.eaglebookstore.model.Usuario;

public interface TokenJwtService {
    public String generateJwt(Usuario user);
}
