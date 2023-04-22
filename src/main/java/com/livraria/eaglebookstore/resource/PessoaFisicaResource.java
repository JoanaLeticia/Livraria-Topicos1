package com.livraria.eaglebookstore.resource;

import com.livraria.eaglebookstore.model.PessoaFisica;
import com.livraria.eaglebookstore.repository.PessoaFisicaRepository;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/pessoafisica")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PessoaFisicaResource {

    private static final Logger LOG = Logger.getLogger(PessoaFisicaResource.class);

    @Inject
    PessoaFisicaRepository pessoaFisicaRepository;

    @GET
    public List<PessoaFisica> listar() {
        return pessoaFisicaRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public PessoaFisica buscarPorId(@PathParam("id") Long id) {
        return pessoaFisicaRepository.findById(id);
    }

    @POST
    @Transactional
    public Response adicionar(PessoaFisica pessoaFisica) {
        pessoaFisicaRepository.persist(pessoaFisica);
        LOG.info("Pessoa Física adicionada com sucesso.");
        return Response.status(Response.Status.CREATED).entity(pessoaFisica).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response atualizar(@PathParam("id") Long id, PessoaFisica pessoaFisica) {
        PessoaFisica pf = pessoaFisicaRepository.findById(id);
        if (pf == null) {
            LOG.info("Pessoa Física não encontrada.");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        pf.setNome(pessoaFisica.getNome());
        pf.setCpf(pessoaFisica.getCpf());
        pf.setEmail(pessoaFisica.getEmail());
        pessoaFisicaRepository.persist(pf);
        LOG.info("Pessoa Física atualizada com sucesso.");
        return Response.status(Response.Status.OK).entity(pf).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response excluir(@PathParam("id") Long id) {
        PessoaFisica pessoaFisica = pessoaFisicaRepository.findById(id);
        if (pessoaFisica == null) {
            LOG.info("Pessoa Física não encontrada.");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        pessoaFisicaRepository.delete(pessoaFisica);
        LOG.info("Pessoa Física excluída com sucesso.");
        return Response.status(Response.Status.OK).build();
    }
}
