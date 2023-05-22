package com.livraria;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import com.livraria.eaglebookstore.dto.ProdutoDTO;
import com.livraria.eaglebookstore.dto.ProdutoResponseDTO;
import com.livraria.eaglebookstore.service.ProdutoService;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import jakarta.inject.Inject;

@QuarkusTest
public class ProdutoResourceTest {

    @Inject
    ProdutoService produtoService;

    @Test
    public void testListarProdutos() {
        given()
          .when().get("/produtos")
          .then()
             .statusCode(200);
    }

    @Test
    public void testCadastrarProduto() {
        ProdutoDTO produto = new ProdutoDTO(
            "nome teste",
            "descricao teste",
            55.0,
            20
        );

        given()
          .contentType(ContentType.JSON)
          .body(produto)
          .when().post("/produtos")
          .then()
             .statusCode(201)
             .body("id", notNullValue(),
              	 "nome", is("nome teste"),
             	 "descricao", is("descricao teste"),
                 "preco", is(55.0F),
                 "estoque", is(20));
    }

    @Test
    public void testAtualizarProduto() {
        ProdutoDTO produto = new ProdutoDTO(
            "nome teste",
            "descricao teste",
            55.0,
            20
        );
        Long id = produtoService.cadastrarProduto(produto).id();

        ProdutoDTO produtoAtualizar = new ProdutoDTO(
            "Livro 01",
            "Um livro sem descrição",
            25.0,
            20
        );

        given()
          .contentType(ContentType.JSON)
          .body(produtoAtualizar)
          .when().put("/produtos/" + id)
          .then()
             .statusCode(204);

        ProdutoResponseDTO produtoResponse = produtoService.buscarProdutoPorId(id);
        assertThat(produtoResponse.nome(), is("Livro 01"));
        assertThat(produtoResponse.descricao(), is("Um livro sem descrição"));
        assertThat(produtoResponse.preco(), is(25.0));
        assertThat(produtoResponse.estoque(), is(20));
    }

    @Test
    public void testExcluirProduto() {
        ProdutoDTO produto = new ProdutoDTO(
            "nome teste",
            "descricao teste",
            55.0,
            20
        );
        Long id = produtoService.cadastrarProduto(produto).id();

        given()
          .when().delete("/produtos/" + id)
          .then()
             .statusCode(204);

        ProdutoResponseDTO produtoResponse = null;
        try {
            produtoResponse =  produtoService.buscarProdutoPorId(id);
        } catch (Exception e) {

        }
        finally {
            assertNull(produtoResponse);   
        }   

    }

}