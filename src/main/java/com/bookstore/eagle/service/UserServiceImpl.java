package com.bookstore.eagle.service;

import java.util.List;
import java.util.stream.Collectors;

import com.bookstore.eagle.dto.UserResponseDTO;
import com.bookstore.eagle.model.User;
import com.bookstore.eagle.repository.UserRepository;
import jakarta.inject.Inject;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class UserServiceImpl implements UserService {
    @Inject
    private UserRepository userRepository;
    
    @Inject
    private Validator validator;

    @Override
    public List<UserResponseDTO> getAll() {
        List<User> list = userRepository.listAll();
        return list.stream().map(u -> UserResponseDTO.valueOf(u)).collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO findById(Long id) {
        User naturalPerson = userRepository.findById(id);
        
        if (naturalPerson == null)
            throw new NotFoundException("The user not found by name.");
        return UserResponseDTO.valueOf(naturalPerson);
    }

    @Override
    public User findByLoginAndPassword(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password);
    }

    @Override
    public UserResponseDTO findByLogin(String login) {
        User user = userRepository.findByLogin(login);
        if (user == null)
            throw new NotFoundException("The user was not found by login.");
        return UserResponseDTO.valueOf(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserResponseDTO> findByName(String name) {
        List<User> list = userRepository.findByName(name);
        return list.stream().map(u -> UserResponseDTO.valueOf(u)).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return userRepository.count();
    }

}

    
