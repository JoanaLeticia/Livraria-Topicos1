package com.bookstore.eagle.resource;

import java.util.List;

import com.bookstore.eagle.application.Result;
import com.bookstore.eagle.dto.OrderDTO;
import com.bookstore.eagle.dto.OrderResponseDTO;
import com.bookstore.eagle.service.OrderService;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.inject.Inject;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    @Inject
    OrderService orderService;

    public OrderResource(OrderService orderService) {
        this.orderService = orderService;
    }

    @GET
    public List<OrderResponseDTO> listOrders() {
        return orderService.listOrders();
    }

    @GET
    @Path("/{id}")
    public OrderResponseDTO searchOrderById(@PathParam("id") Long id) {
        return orderService.searchOrderById(id);
    }

    @POST
    public Response addOrder(OrderDTO dto) {
        try {
            OrderResponseDTO order = orderService.addOrder(dto);
            return Response.status(Status.CREATED).entity(order).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateOrder(@PathParam("id") Long id, OrderDTO dto) {
        try {
            orderService.updateOrder(id, dto);
            return Response.status(Status.NO_CONTENT).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }      
    }

    @DELETE
    @Path("/{id}")
    public Response deleteOrder(@PathParam("id") Long id) {
        orderService.deleteOrder(id);
        return Response.status(Status.NO_CONTENT).build();
    }
}

