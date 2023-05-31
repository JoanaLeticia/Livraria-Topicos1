package com.bookstore.eagle.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.Response.ResponseBuilder;

import java.util.List;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.bookstore.eagle.application.Result;
import com.bookstore.eagle.dto.ProductDTO;
import com.bookstore.eagle.dto.ProductResponseDTO;
import com.bookstore.eagle.service.ProductService;
import com.bookstore.eagle.service.FileService;
import com.bookstore.eagle.form.ImageForm;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    JsonWebToken jwt;

    @Inject
    ProductService productService;

    @Inject
    FileService fileService;

    public ProductResource(ProductService productService, FileService fileService) {
        this.productService = productService;
        this.fileService = fileService;
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

    @PATCH
    @Path("/{id}/new_image")
    @RolesAllowed({"Admin","User"})
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response saveProductImage(@PathParam("id") Long id, @MultipartForm ImageForm form){
        String imageName = "";

        try {
            imageName = fileService.saveProductImage(form.getImage(), form.getImageName());
        } catch (IOException e) {
            Result result = new Result(e.getMessage());
            return Response.status(Status.CONFLICT).entity(result).build();
        }

        productService.updateProductImage(id, imageName);

        return Response.ok().build();
    }

    @GET
    @Path("/download/{newImage}")
    @RolesAllowed({ "Admin", "User" })
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadImage(@PathParam("newImage") String newImage) {
        File imageFile = fileService.download(newImage);

        if (imageFile.exists()) {
            try {
                byte[] imageData = Files.readAllBytes(imageFile.toPath());
                ResponseBuilder response = Response.ok(imageData);
                response.header("Content-Disposition", "attachment; filename=" + newImage);
                return response.build();
            } catch (IOException e) {
                return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Failed to read image file").build();
            }
        } else {
            return Response.status(Status.NOT_FOUND).entity("Image not found").build();
        }
    }
}