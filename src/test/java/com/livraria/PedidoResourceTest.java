package com.livraria;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import com.livraria.eaglebookstore.dto.PedidoDTO;
import com.livraria.eaglebookstore.dto.PedidoResponseDTO;
import com.livraria.eaglebookstore.service.PedidoService;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import javax.inject.Inject;

@QuarkusTest
public class PedidoResourceTest {

    @Inject
    PedidoService pedidoService;

    @Test
    public void testListarPedidos() {
        given()
                .when().get("/pedidos")
                .then()
                .statusCode(200);
    }

    @Test
    public void testCadastrarPedido() {
        PedidoDTO pedido = new PedidoDTO(
                2,
                30.0,
                null,
                2,
                null,
                null,
                null);

        given()
                .contentType(ContentType.JSON)
                .body(pedido)
                .when().post("/pedidos")
                .then()
                .statusCode(201)
                .body("id", notNullValue(),
                        "quantidadeTotal", is(2),
                        "valorTotal", is(30.0),
                        "data", is(null),
                        "status", is(2),
                        "clienteId", is(null),
                        "itensPedido", is(null),
                        "enderecoId", is(null));
    }

    @Test
    public void testAtualizarPedido() {
        PedidoDTO pedido = new PedidoDTO(
                2,
                30.0,
                null,
                2,
                null,
                null,
                null);
        Long id = pedidoService.cadastrarPedido(pedido).id();

        PedidoDTO pedidoAtualizar = new PedidoDTO(
                5,
                35.0,
                null,
                4,
                null,
                null,
                null);

        given()
                .contentType(ContentType.JSON)
                .body(pedidoAtualizar)
                .when().put("/pedidos/" + id)
                .then()
                .statusCode(204);

        PedidoResponseDTO pedidoResponse = pedidoService.buscarPedidoPorId(id);
        assertThat(pedidoResponse.quantidadeTotal(), is(5));
        assertThat(pedidoResponse.valorTotal(), is(35.0));
        assertThat(pedidoResponse.data(), is(null));
        assertThat(pedidoResponse.status(), is(4));
    }

    @Test
    public void testExcluirPedido() {
        PedidoDTO pedido = new PedidoDTO(
                2,
                30.0,
                null,
                2,
                null,
                null,
                null);
        Long id = pedidoService.cadastrarPedido(pedido).id();

        given()
                .when().delete("/pedidos/" + id)
                .then()
                .statusCode(204);

        PedidoResponseDTO pedidoResponse = null;
        try {
            pedidoResponse = pedidoService.buscarPedidoPorId(id);
        } catch (Exception e) {

        } finally {
            assertNull(pedidoResponse);
        }

    }

}