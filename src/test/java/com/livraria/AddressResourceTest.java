package com.livraria;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import com.bookstore.eagle.dto.AddressDTO;
import com.bookstore.eagle.dto.AddressResponseDTO;
import com.bookstore.eagle.service.AddressService;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertNull;

import jakarta.inject.Inject;

@QuarkusTest
public class AddressResourceTest {

    @Inject
    AddressService addressService;

    @Test
    public void testListAddresses() {
        given()
          .when().get("/addresses")
          .then()
             .statusCode(200);
    }

    @Test
    public void testAddAddress() {
        AddressDTO address = new AddressDTO(
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
          .body(address)
          .when().post("/addresses")
          .then()
             .statusCode(201)
             .body("id", notNullValue(),
              	 "street", is("Rua Teste"),
             	 "number", is("123"),
                 "adjunct", is("Casa"),
                 "neighborhood", is("Centro"),
                 "zip", is("12345678"),
                 "cityId", nullValue(),
                 "clientId", nullValue());
    }

    @Test
    public void testUpdateAddress() {
        AddressDTO address = new AddressDTO(
            true,
            "Rua Custodia",
            "156",
            "Casa",
            "Centro",
            "12345678",
            null, 
            null
        );
        Long id = addressService.addAddress(address).id();

        AddressDTO addressUpdate = new AddressDTO(true,
        "Rua Almeida",
        "563",
        "AP",
        "Centro",
        "87654321",
        null,
        null
        );

        given()
          .contentType(ContentType.JSON)
          .body(addressUpdate)
          .when().put("/addresses/" + id)
          .then()
             .statusCode(204);

        AddressResponseDTO addressResponse = addressService.searchAddressById(id);
        assertThat(addressResponse.main(), is(true));
        assertThat(addressResponse.street(), is("Rua Almeida"));
        assertThat(addressResponse.number(), is("563"));
        assertThat(addressResponse.adjunct(), is("AP"));
        assertThat(addressResponse.neighborhood(), is("Centro"));
        assertThat(addressResponse.zip(), is("87654321"));
    }

    @Test
    public void testDeleteAddress() {
        AddressDTO address = new AddressDTO(
            true,
            "Rua teste",
            "123",
            "Casa",
            "Centro",
            "12345678",
            null,
            null
        );
        Long id = addressService.addAddress(address).id();

        given()
          .when().delete("/addresses/" + id)
          .then()
             .statusCode(204);

        AddressResponseDTO addressResponse = null;
        try {
            addressResponse =  addressService.searchAddressById(id);
        } catch (Exception e) {

        }
        finally {
            assertNull(addressResponse);   
        }   

    }

}