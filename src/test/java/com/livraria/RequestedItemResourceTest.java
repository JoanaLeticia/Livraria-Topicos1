package com.livraria;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import com.bookstore.eagle.dto.RequestedItemDTO;
import com.bookstore.eagle.dto.RequestedItemResponseDTO;
import com.bookstore.eagle.service.RequestedItemService;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertNull;

import jakarta.inject.Inject;

@QuarkusTest
public class RequestedItemResourceTest {

    @Inject
    RequestedItemService requestedItemService;

    @Test
    public void testListRequestedItems() {
        given()
                .when().get("/requesteditems")
                .then()
                .statusCode(200);
    }

    @Test
    public void testAddRequestedItem() {
        RequestedItemDTO requestedItem = new RequestedItemDTO(
                1,
                30.0,
                null,
                null);

        given()
                .contentType(ContentType.JSON)
                .body(requestedItem)
                .when().post("/requesteditems")
                .then()
                .statusCode(201)
                .body("id", notNullValue(),
                        "price", is(30.0F),
                        "productId", is(nullValue()),
                        "orderId", is(nullValue()));
    }

    @Test
    public void testUpdateRequestedItem() {
        RequestedItemDTO requestedItem = new RequestedItemDTO(
                1,
                30.0,
                null,
                null);
        Long id = requestedItemService.addRequestedItem(requestedItem).id();

        RequestedItemDTO requestedItemUpdate = new RequestedItemDTO(
                2,
                45.0,
                null,
                null);

        given()
                .contentType(ContentType.JSON)
                .body(requestedItemUpdate)
                .when().put("/requesteditems/" + id)
                .then()
                .statusCode(204);

        RequestedItemResponseDTO requestedItemResponse = requestedItemService.searchRequestedItemById(id);
        assertThat(requestedItemResponse.quantity(), is(2));
        assertThat(requestedItemResponse.price(), is(45.0));
    }

    @Test
    public void testDeleteRequestedItem() {
        RequestedItemDTO requestedItem = new RequestedItemDTO(
                1,
                30.0,
                null,
                null);
        Long id = requestedItemService.addRequestedItem(requestedItem).id();

        given()
                .when().delete("/requesteditems/" + id)
                .then()
                .statusCode(204);

        RequestedItemResponseDTO requestedItemResponse = null;
        try {
                requestedItemResponse = requestedItemService.searchRequestedItemById(id);
        } catch (Exception e) {

        } finally {
            assertNull(requestedItemResponse);
        }

    }

}