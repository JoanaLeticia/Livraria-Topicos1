package com.bookstore.eagle.service;

import java.util.List;

import com.bookstore.eagle.dto.BookDTO;
import com.bookstore.eagle.dto.BookResponseDTO;

public interface BookService {

    BookResponseDTO addBook(BookDTO bookDTO);

    BookResponseDTO searchBookById(Long id);

    BookResponseDTO updateBook(Long id, BookDTO bookDTO);

    void deleteBook(Long id);

    List<BookResponseDTO> listBooks();

}
