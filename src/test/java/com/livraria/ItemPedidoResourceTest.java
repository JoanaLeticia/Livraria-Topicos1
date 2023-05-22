package com.livraria;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import com.livraria.eaglebookstore.dto.ItemPedidoDTO;
import com.livraria.eaglebookstore.dto.ItemPedidoResponseDTO;
import com.livraria.eaglebookstore.service.ItemPedidoService;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertNull;

import jakarta.inject.Inject;

@QuarkusTest
public class ItemPedidoResourceTest {

    @Inject
    ItemPedidoService ipService;

    @Test
    public void testListarItensPedido() {
        given()
                .when().get("/itenspedidos")
                .then()
                .statusCode(200);
    }

    @Test
    public void testCadastrarItemPedido() {
        ItemPedidoDTO ip = new ItemPedidoDTO(
                1,
                30.0,
                null,
                null);

        given()
                .contentType(ContentType.JSON)
                .body(ip)
                .when().post("/itenspedidos")
                .then()
                .statusCode(201)
                .body("id", notNullValue(),
                        "preco", is(30.0F),
                        "produtoId", is(nullValue()),
                        "pedidoId", is(nullValue()));
    }

    @Test
    public void testAtualizarItemPedido() {
        ItemPedidoDTO ip = new ItemPedidoDTO(
                1,
                30.0,
                null,
                null);
        Long id = ipService.cadastrarItemPedido(ip).id();

        ItemPedidoDTO ipAtualizar = new ItemPedidoDTO(
                2,
                45.0,
                null,
                null);

        given()
                .contentType(ContentType.JSON)
                .body(ipAtualizar)
                .when().put("/itenspedidos/" + id)
                .then()
                .statusCode(204);

        ItemPedidoResponseDTO ipResponse = ipService.buscarItemPedidoPorId(id);
        assertThat(ipResponse.quantidade(), is(2));
        assertThat(ipResponse.preco(), is(45.0));
    }

    @Test
    public void testExcluirItemPedido() {
        ItemPedidoDTO ip = new ItemPedidoDTO(
                1,
                30.0,
                null,
                null);
        Long id = ipService.cadastrarItemPedido(ip).id();

        given()
                .when().delete("/itenspedidos/" + id)
                .then()
                .statusCode(204);

        ItemPedidoResponseDTO ipResponse = null;
        try {
            ipResponse = ipService.buscarItemPedidoPorId(id);
        } catch (Exception e) {

        } finally {
            assertNull(ipResponse);
        }

    }

}