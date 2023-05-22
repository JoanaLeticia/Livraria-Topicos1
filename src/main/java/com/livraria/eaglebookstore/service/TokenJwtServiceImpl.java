package com.livraria.eaglebookstore.service;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import io.smallrye.jwt.build.Jwt;

import com.livraria.eaglebookstore.model.Usuario;

public class TokenJwtServiceImpl implements TokenJwtService {
    
    private static final Duration EXPIRATION_TIME = Duration.ofHours(24);

    @Override
    public String generateJwt(Usuario user) {
        Instant now = Instant.now();
        Instant expiryDate = now.plus(EXPIRATION_TIME);

        Set<String> roles = user.getPerfis()
                .stream().map(p -> p.getLabel())
                .collect(Collectors.toSet());

        return Jwt.issuer("eaglebookstore-jwt")
            .subject(user.getLogin())
            .groups(roles)
            .expiresAt(expiryDate)
            .sign();

    }

}
