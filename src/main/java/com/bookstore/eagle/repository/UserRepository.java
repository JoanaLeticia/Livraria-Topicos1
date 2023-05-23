package com.bookstore.eagle.repository;

import java.util.List;

import com.bookstore.eagle.model.User;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

public class UserRepository implements PanacheRepository<User> {
    
    public List<User> findByName(String name) {
        if (name == null)
            return null;

        return find("UPPER(naturalPerson.name) LIKE ?1 ", "%" + name.toUpperCase() + "%").list();
    }

    public User findByLoginAndPassword(String login, String password) {
        if (login == null || password == null)
            return null;

        return find("login = ?1 AND password = ?2 ", login, password).firstResult();
    }

    public User findByLogin(String login) {
        if (login == null)
            return null;

        return find("login = ?1 ", login).firstResult();
    }

}
