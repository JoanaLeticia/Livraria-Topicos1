package com.livraria;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import com.bookstore.eagle.dto.OrderDTO;
import com.bookstore.eagle.dto.OrderResponseDTO;
import com.bookstore.eagle.service.OrderService;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertNull;

import jakarta.inject.Inject;

@QuarkusTest
public class OrderResourceTest {

    @Inject
    OrderService orderService;

    @Test
    public void testListOrders() {
        given()
          .when().get("/orders")
          .then()
             .statusCode(200);
    }

    @Test
    public void testAddOrder() {
        OrderDTO order = new OrderDTO(
                2,
                30.0,
                null,
                2,
                null,
                null,
                null
        );

        given()
          .contentType(ContentType.JSON)
          .body(order)
          .when().post("/orders")
          .then()
             .statusCode(201)   
             .body("id", notNullValue(),
                    "totalQuantity", is(2),
                        "totalValue", is(30.0F),
                        "date", nullValue(),
                        "status.label", is("Processando"),
                        "clientId", nullValue(),
                        "requestedItem", nullValue(),
                        "addressId", nullValue());
    }

    @Test
    public void testUpdateOrders() {
        OrderDTO order = new OrderDTO(
                2,
                30.0,
                null,
                2,
                null,
                null,
                null
        );
        Long id = orderService.addOrder(order).id();

        OrderDTO orderUpdate = new OrderDTO(
                5,
                35.0,
                null,
                4,
                null,
                null,
                null
        );

        given()
          .contentType(ContentType.JSON)
          .body(orderUpdate)
          .when().put("/orders/" + id)
          .then()
             .statusCode(204);

        OrderResponseDTO orderResponse = orderService.searchOrderById(id);
        assertThat(orderResponse.totalQuantity(), is(5));
        assertThat(orderResponse.totalValue(), is(35.0));
    }

    @Test
    public void testDeleteOrder() {
        OrderDTO order = new OrderDTO(
                2,
                30.0,
                null,
                2,
                null,
                null,
                null
        );
        Long id = orderService.addOrder(order).id();

        given()
          .when().delete("/orders/" + id)
          .then()
             .statusCode(204);

        OrderResponseDTO orderResponse = null;
        try {
                orderResponse =  orderService.searchOrderById(id);
        } catch (Exception e) {

        }
        finally {
            assertNull(orderResponse);   
        }   

    }

}