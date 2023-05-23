package com.bookstore.eagle.resource;

import java.util.List;

import com.bookstore.eagle.application.Result;
import com.bookstore.eagle.dto.RequestedItemDTO;
import com.bookstore.eagle.dto.RequestedItemResponseDTO;
import com.bookstore.eagle.service.RequestedItemService;

import jakarta.inject.Inject;
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

@Path("/requesteditems")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RequestedItemResource {

    @Inject
    RequestedItemService requestedItemService;

    @GET
    public List<RequestedItemResponseDTO> listRequestedItems() {
        return requestedItemService.listRequestedItems();
    }

    @GET
    @Path("/{id}")
    public RequestedItemResponseDTO searchRequestedItemById(@PathParam("id") Long id) {
        return requestedItemService.searchRequestedItemById(id);
    }

    @POST
    public Response addRequestedItem(RequestedItemDTO dto) {
        try {
            RequestedItemResponseDTO requestedItem = requestedItemService.addRequestedItem(dto);
            return Response.status(Status.CREATED).entity(requestedItem).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateRequestedItem(@PathParam("id") Long id, RequestedItemDTO dto) {
        try {
            requestedItemService.updateRequestedItem(id, dto);
            return Response.status(Status.NO_CONTENT).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }      
    }

    @DELETE
    @Path("/{id}")
    public Response deleteRequestedItem(@PathParam("id") Long id) {
        requestedItemService.deleteRequestedItem(id);
        return Response.status(Status.NO_CONTENT).build();
    }
}
