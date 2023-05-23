package com.bookstore.eagle.resource;

import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import java.util.List;

import com.bookstore.eagle.application.Result;
import com.bookstore.eagle.dto.BookDTO;
import com.bookstore.eagle.dto.BookResponseDTO;
import com.bookstore.eagle.service.BookService;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

    @Inject
    BookService bookService;

    @GET
    public List<BookResponseDTO> listBooks() {
        return bookService.listBooks();
    }

    @GET
    @Path("/{id}")
    public BookResponseDTO searchBookById(@PathParam("id") Long id) {
        return bookService.searchBookById(id);
    }

    @POST
    public Response addBook(BookDTO dto) {
        try {
            BookResponseDTO book = bookService.addBook(dto);
            return Response.status(Status.CREATED).entity(book).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateBook(@PathParam("id") Long id, BookDTO dto) {
        try {
            bookService.updateBook(id, dto);;
            return Response.status(Status.NO_CONTENT).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }


    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") Long id) {
        bookService.deleteBook(id);
        return Response.status(Status.NO_CONTENT).build();
    }
    
}