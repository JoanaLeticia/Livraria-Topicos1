package com.bookstore.eagle.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;

import com.bookstore.eagle.dto.UserAuthenticationDTO;
import com.bookstore.eagle.model.User;
import com.bookstore.eagle.service.HashService;
import com.bookstore.eagle.service.TokenJwtService;
import com.bookstore.eagle.service.UserService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    HashService hashService;

    @Inject
    UserService userService;

    @Inject
    TokenJwtService tokenService;

    @Inject
    JsonWebToken jwt;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(UserAuthenticationDTO authDTO) {
        String hash = hashService.getPasswordHash(authDTO.password());

        User user = userService.findByLoginAndPassword(authDTO.login(), hash);

        if (user == null) {
            return Response.status(Status.NO_CONTENT)
                .entity("User not found.").build();
        }
        return Response.ok()
            .header("Authorization", tokenService.generateJwt(user))
            .build();
    }

}
