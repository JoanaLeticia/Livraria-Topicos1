package com.bookstore.eagle.resource;

import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import java.util.List;

import com.bookstore.eagle.application.Result;
import com.bookstore.eagle.dto.ProductDTO;
import com.bookstore.eagle.dto.ProductResponseDTO;
import com.bookstore.eagle.service.ProductService;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GET
    public List<ProductResponseDTO> listProducts() {
        return productService.listProducts();
    }

    @GET
    @Path("/{id}")
    public ProductResponseDTO searchProductById(@PathParam("id") Long id) {
        return productService.searchProductById(id);
    }

    @POST
    public Response addProduct(ProductDTO dto) {
        try{
            ProductResponseDTO product = productService.addProduct(dto);
            return Response.status(Status.CREATED).entity(product).build();
        } catch(ConstraintViolationException e){
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }
    
    @PUT
    @Path("/{id}")
    public Response updateProduct(@PathParam("id") Long id, ProductDTO dto) {
        try {
            productService.updateProduct(id, dto);
            return Response.status(Status.NO_CONTENT).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteProduct(@PathParam("id") Long id) {
        productService.deleteProduct(id);
        return Response.status(Status.NO_CONTENT).build();
    }

}