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

import java.io.IOException;
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
    @Path("/newimage")
    @RolesAllowed({"Admin","User"})
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response saveImage(@MultipartForm ImageForm form){
        String imageName = "";

        try {
            imageName = fileService.saveProductImage(form.getImage(), form.getImageName());
        } catch (IOException e) {
            Result result = new Result(e.getMessage());
            return Response.status(Status.CONFLICT).entity(result).build();
        }

        String idS = jwt.getSubject();
        Long id = Long.parseLong(idS);
        ProductResponseDTO product = productService.searchProductById(id);

        product = productService.updateProductImage(product.id(), imageName);

        return Response.ok(product).build();

    }

    @GET
    @Path("/download/{imageName}")
    @RolesAllowed({"Admin","User"})
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("imageName") String imageName) {
        ResponseBuilder response = Response.ok(fileService.download(imageName));
        response.header("Content-Disposition", "attachment;filename="+imageName);
        return response.build();
    }
}