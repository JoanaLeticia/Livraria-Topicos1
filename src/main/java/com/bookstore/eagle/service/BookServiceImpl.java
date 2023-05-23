package com.bookstore.eagle.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.bookstore.eagle.dto.BookDTO;
import com.bookstore.eagle.dto.BookResponseDTO;
import com.bookstore.eagle.model.Genre;
import com.bookstore.eagle.model.Book;
import com.bookstore.eagle.model.AgeRating;
import com.bookstore.eagle.repository.BookRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class BookServiceImpl implements BookService {
    @Inject
    BookRepository bookRepository;

    @Inject
    Validator validator;

    @Override
    public List<BookResponseDTO> listBooks() {
        List<Book> list = bookRepository.listAll();
        return list.stream().map(BookResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public BookResponseDTO searchBookById(Long id) {
        Book book = bookRepository.findById(id);
        if (book == null)
            throw new NotFoundException("The book was not found.");
        return new BookResponseDTO(book);
    }

    @Override
    @Transactional
    public BookResponseDTO addBook(BookDTO bookDTO) throws ConstraintViolationException {
        validating(bookDTO);

        Book entity = new Book();
        entity.setName(bookDTO.name());
        entity.setDescription(bookDTO.description());
        entity.setAuthor(bookDTO.author());
        entity.setPublisher(bookDTO.publisher());
        entity.setGenre(Genre.valueOf(bookDTO.genre()));
        entity.setRating(AgeRating.valueOf(bookDTO.rating()));
        entity.setPrice(bookDTO.price());
        entity.setStock(bookDTO.stock());
        bookRepository.persist(entity);

        return new BookResponseDTO(entity);

    }

    @Override
    @Transactional
    public BookResponseDTO updateBook(Long id, BookDTO bookDTO) throws ConstraintViolationException {
        validating(bookDTO);

        Book entity = bookRepository.findById(id);

        entity.setName(bookDTO.name());
        entity.setDescription(bookDTO.description());
        entity.setAuthor(bookDTO.author());
        entity.setPublisher(bookDTO.publisher());
        entity.setGenre(Genre.valueOf(bookDTO.genre()));
        entity.setRating(AgeRating.valueOf(bookDTO.rating()));
        entity.setPrice(bookDTO.price());
        entity.setStock(bookDTO.stock());
        
        return new BookResponseDTO(entity);
    }

    private void validating(BookDTO bookDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<BookDTO>> violations = validator.validate(bookDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

}
