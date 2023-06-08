package com.bookstore.eagle.resource;

import java.util.List;

import com.bookstore.eagle.application.Result;
import com.bookstore.eagle.dto.AddressDTO;
import com.bookstore.eagle.dto.AddressResponseDTO;
import com.bookstore.eagle.service.AddressService;

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

@Path("/addresses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AddressResource {

    @Inject
    AddressService addressService;

    @GET
    public List<AddressResponseDTO> listAddresses() {
        return addressService.listAddresses();
    }

    @GET
    @Path("/{id}")
    public AddressResponseDTO searchAddressById(@PathParam("id") Long id) {
        return addressService.searchAddressById(id);
    }

    @POST
    public Response addAddress(AddressDTO dto) {
        try{
            AddressResponseDTO address = addressService.addAddress(dto);
            return Response.status(Status.CREATED).entity(address).build();
        } catch(ConstraintViolationException e){
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateAddress(@PathParam("id") Long id, AddressDTO dto) {
        try {
            addressService.updateAddress(id, dto);
            return Response.status(Status.NO_CONTENT).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }      
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAddress(@PathParam("id") Long id) {
        addressService.deleteAddress(id);
        return Response.status(Status.NO_CONTENT).build();
    }

}
