package com.livraria;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import com.livraria.eaglebookstore.dto.EnderecoDTO;
import com.livraria.eaglebookstore.dto.EnderecoResponseDTO;
import com.livraria.eaglebookstore.service.EnderecoService;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertNull;

import jakarta.inject.Inject;

@QuarkusTest
public class EnderecoResourceTest {

    @Inject
    EnderecoService endService;

    @Test
    public void testListarEnderecos() {
        given()
          .when().get("/enderecos")
          .then()
             .statusCode(200);
    }

    @Test
    public void testCadastrarEndereco() {
        EnderecoDTO end = new EnderecoDTO(
            true,
            "Rua Teste",
            "123",
            "Casa",
            "Centro",
            "12345678",
            null,
            null
        );

        given()
          .contentType(ContentType.JSON)
          .body(end)
          .when().post("/enderecos")
          .then()
             .statusCode(201)
             .body("id", notNullValue(),
              	 "logradouro", is("Rua Teste"),
             	 "numero", is("123"),
                 "complemento", is("Casa"),
                 "bairro", is("Centro"),
                 "cep", is("12345678"),
                 "municipioId", nullValue(),
                 "clienteId", nullValue());
    }

    @Test
    public void testAtualizarEndereco() {
        EnderecoDTO end = new EnderecoDTO(
            true,
            "Rua teste",
            "123",
            "Casa",
            "Centro",
            "12345678",
            null,
            null
        );
        Long id = endService.cadastrarEndereco(end).id();

        EnderecoDTO endAtualizar = new EnderecoDTO(
            true,
            "Rua Custodia",
            "231",
            "Casa",
            "Centro",
            "12345678",
            null,
            null
        );

        given()
          .contentType(ContentType.JSON)
          .body(endAtualizar)
          .when().put("/enderecos/" + id)
          .then()
             .statusCode(204);

        EnderecoResponseDTO endResponse = endService.buscarEnderecoPorId(id);
        assertThat(endResponse.principal(), is(true));
        assertThat(endResponse.logradouro(), is("Rua Custodia"));
        assertThat(endResponse.numero(), is("231"));
        assertThat(endResponse.complemento(), is("Casa"));
        assertThat(endResponse.bairro(), is("Centro"));
        assertThat(endResponse.cep(), is("12345678"));
    }

    @Test
    public void testExcluirEndereco() {
        EnderecoDTO end = new EnderecoDTO(
            true,
            "Rua teste",
            "123",
            "Casa",
            "Centro",
            "12345678",
            null,
            null
        );
        Long id = endService.cadastrarEndereco(end).id();

        given()
          .when().delete("/enderecos/" + id)
          .then()
             .statusCode(204);

        EnderecoResponseDTO endResponse = null;
        try {
            endResponse =  endService.buscarEnderecoPorId(id);
        } catch (Exception e) {

        }
        finally {
            assertNull(endResponse);   
        }   

    }

}