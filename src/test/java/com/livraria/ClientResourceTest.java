package com.livraria;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import com.bookstore.eagle.dto.ClientDTO;
import com.bookstore.eagle.dto.ClientResponseDTO;
import com.bookstore.eagle.service.ClientService;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import jakarta.inject.Inject;

@QuarkusTest
public class ClientResourceTest {

    @Inject
    ClientService cService;

    @Test
    public void testListClients() {
        given()
          .when().get("/clients")
          .then()
             .statusCode(200);
    }

    @Test
    public void testAddClient() {
        ClientDTO client = new ClientDTO(
            "teste@teste.com",
            null,
            null,
            null,
            null
        );

        given()
          .contentType(ContentType.JSON)
          .body(client)
          .when().post("/clients")
          .then()
             .statusCode(201)
             .body("id", notNullValue(),
              	 "email", is("teste@teste.com"),
             	 "phones", nullValue(),
                 "addresses", nullValue(),
                 "orders", nullValue(),
                 "wishlists", nullValue());
    }

    @Test
    public void testUpdateClient() {
        ClientDTO client = new ClientDTO(
            "teste@teste.com",
            null,
            null,
            null,
            null
        );
        Long id = cService.addClient(client).id();

        ClientDTO clientUpdate = new ClientDTO(
            "joana@teste.com",
            null,
            null,
            null,
            null
        );

        given()
          .contentType(ContentType.JSON)
          .body(clientUpdate)
          .when().put("/clients/" + id)
          .then()
             .statusCode(204);

        ClientResponseDTO clientResponse = cService.searchClientById(id);
        assertThat(clientResponse.email(), is("joana@teste.com"));
    }

    @Test
    public void testDeleteClient() {
        ClientDTO client = new ClientDTO(
            "teste@teste.com",
            null,
            null,
            null,
            null
        );
        Long id = cService.addClient(client).id();

        given()
          .when().delete("/clients/" + id)
          .then()
             .statusCode(204);

        ClientResponseDTO clientResponse = null;
        try {
            clientResponse =  cService.searchClientById(id);
        } catch (Exception e) {

        }
        finally {
            assertNull(clientResponse);   
        }   

    }

}