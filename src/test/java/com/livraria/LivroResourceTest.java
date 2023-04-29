package com.livraria;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import com.livraria.eaglebookstore.dto.LivroDTO;
import com.livraria.eaglebookstore.dto.LivroResponseDTO;
import com.livraria.eaglebookstore.service.LivroService;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import javax.inject.Inject;

@QuarkusTest
public class LivroResourceTest {

    @Inject
    LivroService livroService;

    @Test
    public void testListarLivros() {
        given()
          .when().get("/livros")
          .then()
             .statusCode(200);
    }

    @Test
    public void testCadastrarLivro() {
        LivroDTO livro = new LivroDTO(
            "Teste nome",
            "Descricao teste",
            "Autor Desconhecido",
            "Editora desconhecida",
            1,
            2,
            25.0,
            50
        );

        given()
          .contentType(ContentType.JSON)
          .body(livro)
          .when().post("/livros")
          .then()
             .statusCode(201)
             .body("id", notNullValue(),
              	 "nome", is("Teste nome"),
             	 "descricao", is("Descricao teste"),
                 "autor", is("Autor desconhecido"),
                 "editora", is("Editora desconhecida"),
                 "genero", is(1),
                 "classificacaoEtaria", is(2),
                 "preco", is(25.0),
                 "estoque", is(50));
    }

    @Test
    public void testAtualizarLivro() {
        LivroDTO livro = new LivroDTO(
            "Teste nome",
            "Descricao teste",
            "Autor Desconhecido",
            "Editora desconhecida",
            1,
            2,
            25.0,
            50
        );
        Long id = livroService.cadastrarLivro(livro).id();

        LivroDTO livroAtualizar = new LivroDTO(
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
          .body(livroAtualizar)
          .when().put("/livros/" + id)
          .then()
             .statusCode(204);

        LivroResponseDTO livroResponse = livroService.buscarLivroPorId(id);
        assertThat(livroResponse.nome(), is("Biblia Sagrada"));
        assertThat(livroResponse.descricao(), is("Livro cristão"));
        assertThat(livroResponse.autor(), is("Autor Desconhecido"));
        assertThat(livroResponse.editora(), is("Editora desconhecida"));
        assertThat(livroResponse.genero(), is(1));
        assertThat(livroResponse.classificacaoEtaria(), is(1));
        assertThat(livroResponse.preco(), is(50.0));
        assertThat(livroResponse.estoque(), is(100));
    }

    @Test
    public void testExcluirLivro() {
        LivroDTO livro = new LivroDTO(
            "Teste nome",
            "Descricao teste",
            "Autor Desconhecido",
            "Editora desconhecida",
            1,
            2,
            25.0,
            50
        );
        Long id = livroService.cadastrarLivro(livro).id();

        given()
          .when().delete("/livros/" + id)
          .then()
             .statusCode(204);

        LivroResponseDTO livroResponse = null;
        try {
            livroResponse =  livroService.buscarLivroPorId(id);
        } catch (Exception e) {

        }
        finally {
            assertNull(livroResponse);   
        }   

    }

}