package com.bookstore.eagle.repository;

import com.bookstore.eagle.model.Book;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BookRepository implements PanacheRepository<Book> {

    public void create(Book book) {
        persist(book);
    }

    public void update(Book book) {
        update("name = ?1, description = ?2, price = ?3, stock = ?4, author = ?5, publisher = ?6, genre = ?7, rating = ?8 where id = ?9",
        book.getName(),
        book.getDescription(),
        book.getPrice(),
        book.getStock(),
        book.getAuthor(),
        book.getPublisher(),
        book.getGenre(),
        book.getRating(),
        book.getId());
    }

    public void delete(Long id) {
        deleteById(id);
    }

    public Book searchById(Long id) {
        return findById(id);
    }

    public Book save(Book existentBook) {
        return null;
    }

}
