package com.bookstore.eagle.service;

import com.bookstore.eagle.model.User;

public interface TokenJwtService {
    public String generateJwt(User user);
}
