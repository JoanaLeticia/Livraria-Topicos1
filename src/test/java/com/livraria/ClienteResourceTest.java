package com.livraria;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import com.livraria.eaglebookstore.dto.ClienteDTO;
import com.livraria.eaglebookstore.dto.ClienteResponseDTO;
import com.livraria.eaglebookstore.service.ClienteService;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import javax.inject.Inject;

@QuarkusTest
public class ClienteResourceTest {

    @Inject
    ClienteService cService;

    @Test
    public void testListarClientes() {
        given()
          .when().get("/clientes")
          .then()
             .statusCode(200);
    }

    @Test
    public void testCadastrarCliente() {
        ClienteDTO cliente = new ClienteDTO(
            "teste@teste.com",
            null,
            null,
            null,
            null
        );

        given()
          .contentType(ContentType.JSON)
          .body(cliente)
          .when().post("/clientes")
          .then()
             .statusCode(201)
             .body("id", notNullValue(),
              	 "email", is("teste@teste.com"),
             	 "telefones", nullValue(),
                 "enderecos", nullValue(),
                 "pedidos", nullValue(),
                 "listaDesejos", nullValue());
    }

    @Test
    public void testAtualizarCliente() {
        ClienteDTO cliente = new ClienteDTO(
            "teste@teste.com",
            null,
            null,
            null,
            null
        );
        Long id = cService.cadastrarCliente(cliente).id();

        ClienteDTO clienteAtualizar = new ClienteDTO(
            "joana@teste.com",
            null,
            null,
            null,
            null
        );

        given()
          .contentType(ContentType.JSON)
          .body(clienteAtualizar)
          .when().put("/clientes/" + id)
          .then()
             .statusCode(204);

        ClienteResponseDTO clienteResponse = cService.buscarClientePorId(id);
        assertThat(clienteResponse.email(), is("joana@teste.com"));
    }

    @Test
    public void testExcluirClientes() {
        ClienteDTO cliente = new ClienteDTO(
            "teste@teste.com",
            null,
            null,
            null,
            null
        );
        Long id = cService.cadastrarCliente(cliente).id();

        given()
          .when().delete("/clientes/" + id)
          .then()
             .statusCode(204);

        ClienteResponseDTO clienteResponse = null;
        try {
            clienteResponse =  cService.buscarClientePorId(id);
        } catch (Exception e) {

        }
        finally {
            assertNull(clienteResponse);   
        }   

    }

}