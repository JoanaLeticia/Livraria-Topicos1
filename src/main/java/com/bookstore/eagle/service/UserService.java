package com.bookstore.eagle.service;

import java.util.List;

import com.bookstore.eagle.dto.UserResponseDTO;
import com.bookstore.eagle.model.User;

public interface UserService {
    List<UserResponseDTO> getAll();

    UserResponseDTO findById(Long id);

    User findByLoginAndPassword(String login, String password);

    UserResponseDTO findByLogin(String login);

    void delete(Long id);

    List<UserResponseDTO> findByName(String name);

    long count();
}
