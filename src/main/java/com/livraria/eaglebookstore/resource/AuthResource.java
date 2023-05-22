package com.livraria.eaglebookstore.resource;

import com.livraria.eaglebookstore.dto.AuthUsuarioDTO;
import com.livraria.eaglebookstore.dto.UsuarioResponseDTO;
import com.livraria.eaglebookstore.model.Usuario;
import com.livraria.eaglebookstore.service.ClienteService;
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

public class AuthResource {

    @Inject
    HashService hashService;

    @Inject
    ClienteService clienteService;

    @Inject
    TokenJwtService tokenService;

    @Inject
    JsonWebToken jwt;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(AuthUsuarioDTO authDTO) {
        String hash = hashService.getHashSenha(authDTO.senha());

        Usuario usuario = usuarioService.findByLoginAndSenha(authDTO.login(), hash);

        if (usuario == null) {
            return Response.status(Status.NO_CONTENT)
                .entity("Usuario não encontrado").build();
        } 
        return Response.ok()
            .header("Authorization", tokenService.generateJwt(usuario))
            .build();
        
    }

    @GET
    @Path("/usuario")
    @RolesAllowed({"User"})
    public Response getPerfilUsuario() {

        String login = jwt.getSubject();
        UsuarioResponseDTO usuario = usuarioService.findByLogin(login);

        return Response.ok(usuario).build();
    }


}
