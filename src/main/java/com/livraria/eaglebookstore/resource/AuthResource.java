package com.livraria.eaglebookstore.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;

import com.livraria.eaglebookstore.dto.AuthUsuarioDTO;
import com.livraria.eaglebookstore.dto.UsuarioResponseDTO;
import com.livraria.eaglebookstore.model.Usuario;
import com.livraria.eaglebookstore.service.UsuarioService;
import com.livraria.eaglebookstore.service.HashService;
import com.livraria.eaglebookstore.service.TokenJwtService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
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
    UsuarioService usuarioService;

    @Inject
    TokenJwtService tokenService;

    @Inject
    JsonWebToken jwt;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(AuthUsuarioDTO authDTO) {
        String hash = hashService.getHashSenha(authDTO.senha());

        Usuario user = usuarioService.findByLoginAndSenha(authDTO.login(), hash);

        if (user == null) {
            return Response.status(Status.NO_CONTENT)
                .entity("Usuario não encontrado.").build();
        }
        return Response.ok()
            .header("Authorization", tokenService.generateJwt(user))
            .build();
    }

    @GET
    @Path("/usuario")
    @RolesAllowed({"User"})
    public Response getPerfilUsuario() {
        String login = jwt.getSubject();
        UsuarioResponseDTO user = usuarioService.findByLogin(login);

        return Response.ok(user).build();
    }


}
