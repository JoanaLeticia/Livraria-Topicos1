package com.livraria;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import com.bookstore.eagle.dto.BookDTO;
import com.bookstore.eagle.dto.BookResponseDTO;
import com.bookstore.eagle.service.BookService;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertNull;

import jakarta.inject.Inject;

@QuarkusTest
public class BookResourceTest {

    @Inject
    BookService bookService;

    @Test
    public void testListBooks() {
        given()
          .when().get("/books")
          .then()
             .statusCode(200);
    }

    @Test
    public void testAddBooks() {
        BookDTO book = new BookDTO(
            "asdas",
            "adfaa",
            "bvcxbv",
            "dafda",
            2,
            4,
            25.0,
            60
        );

        given()
          .contentType(ContentType.JSON)
          .body(book)
          .when().post("/books")
          .then()
             .statusCode(201)
             .body("id", notNullValue(),
              	 "name", is("asdas"),
             	 "description", is("adfaa"),
                 "author", is("bvcxbv"),
                 "publisher", is("dafda"),
                 "genre.label", is("Comedy"),
                 "rating.label", nullValue(),
                 "price", is(25.0F),
                 "stock", is(60));
    }

    @Test
    public void testUpdateBooks() {
        BookDTO book = new BookDTO(
            "Teste nome",
            "Descricao teste",
            "Autor Desconhecido",
            "Editora desconhecida",
            1,
            2,
            25.0,
            50
        );
        Long id = bookService.addBook(book).id();

        BookDTO bookUpdate = new BookDTO(
            "Biblia Sagrada",
            "Livro cristão",
            "Autor Desconhecido",
            "Editora desconhecida",
            1,
            1,
            50.0,
            100
        );

        given()
          .contentType(ContentType.JSON)
          .body(bookUpdate)
          .when().put("/books/" + id)
          .then()
             .statusCode(204);

        BookResponseDTO bookResponse = bookService.searchBookById(id);
        assertThat(bookResponse.name(), is("Biblia Sagrada"));
        assertThat(bookResponse.description(), is("Livro cristão"));
        assertThat(bookResponse.author(), is("Autor Desconhecido"));
        assertThat(bookResponse.publisher(), is("Editora desconhecida"));
        assertThat(bookResponse.price(), is(50.0));
        assertThat(bookResponse.stock(), is(100));
    }

    @Test
    public void testDeleteBook() {
        BookDTO book = new BookDTO(
            "Teste nome",
            "Descricao teste",
            "Autor Desconhecido",
            "Editora desconhecida",
            1,
            2,
            25.0,
            50
        );
        Long id = bookService.addBook(book).id();

        given()
          .when().delete("/books/" + id)
          .then()
             .statusCode(204);

        BookResponseDTO bookResponse = null;
        try {
            bookResponse =  bookService.searchBookById(id);
        } catch (Exception e) {

        }
        finally {
            assertNull(bookResponse);   
        }   

    }

}