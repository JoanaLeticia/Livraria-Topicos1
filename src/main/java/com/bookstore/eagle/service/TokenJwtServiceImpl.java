package com.bookstore.eagle.service;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import com.bookstore.eagle.model.User;

import io.smallrye.jwt.build.Jwt;

public class TokenJwtServiceImpl implements TokenJwtService {
    
    private static final Duration EXPIRATION_TIME = Duration.ofHours(24);

    @Override
    public String generateJwt(User user) {
        Instant now = Instant.now();
        Instant expiryDate = now.plus(EXPIRATION_TIME);

        Set<String> roles = user.getProfiles()
                .stream().map(p -> p.getLabel())
                .collect(Collectors.toSet());

        return Jwt.issuer("eaglebookstore-jwt")
            .subject(user.getLogin())
            .groups(roles)
            .expiresAt(expiryDate)
            .sign();

    }

}
