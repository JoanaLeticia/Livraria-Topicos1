package com.livraria;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import com.bookstore.eagle.dto.ProductDTO;
import com.bookstore.eagle.dto.ProductResponseDTO;
import com.bookstore.eagle.service.ProductService;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import jakarta.inject.Inject;

@QuarkusTest
public class ProductResourceTest {

    @Inject
    ProductService productService;

    @Test
    public void testListProducts() {
        given()
          .when().get("/products")
          .then()
             .statusCode(200);
    }

    @Test
    public void testAddProduct() {
        ProductDTO product = new ProductDTO(
            "nome teste",
            "descricao teste",
            55.0,
            20
        );

        given()
          .contentType(ContentType.JSON)
          .body(product)
          .when().post("/products")
          .then()
             .statusCode(201)
             .body("id", notNullValue(),
              	 "name", is("nome teste"),
             	 "description", is("descricao teste"),
                 "price", is(55.0F),
                 "stock", is(20));
    }

    @Test
    public void testUpdateProduct() {
        ProductDTO product = new ProductDTO(
            "nome teste",
            "descricao teste",
            55.0,
            20
        );
        Long id = productService.addProduct(product).id();

        ProductDTO productUpdate = new ProductDTO(
            "Livro 01",
            "Um livro sem descrição",
            25.0,
            20
        );

        given()
          .contentType(ContentType.JSON)
          .body(productUpdate)
          .when().put("/products/" + id)
          .then()
             .statusCode(204);

        ProductResponseDTO productResponse = productService.searchProductById(id);
        assertThat(productResponse.name(), is("Livro 01"));
        assertThat(productResponse.description(), is("Um livro sem descrição"));
        assertThat(productResponse.price(), is(25.0));
        assertThat(productResponse.stock(), is(20));
    }

    @Test
    public void testDeleteProduct() {
        ProductDTO product = new ProductDTO(
            "nome teste",
            "descricao teste",
            55.0,
            20
        );
        Long id = productService.addProduct(product).id();

        given()
          .when().delete("/products/" + id)
          .then()
             .statusCode(204);

        ProductResponseDTO productResponse = null;
        try {
            productResponse =  productService.searchProductById(id);
        } catch (Exception e) {

        }
        finally {
            assertNull(productResponse);   
        }   

    }

}