package com.livraria.eaglebookstore.resource;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.livraria.eaglebookstore.model.PessoaJuridica;
import com.livraria.eaglebookstore.repository.PessoaJuridicaRepository;

@Path("/pessoajuridica")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PessoaJuridicaResource {

    @Inject
    PessoaJuridicaRepository repository;

    @GET
    public List<PessoaJuridica> getAll() {
        return repository.listAll();
    }

    @GET
    @Path("/{id}")
    public PessoaJuridica findById(@PathParam("id") Long id) {
        return repository.findById(id);
    }

    @POST
    @Transactional
    public PessoaJuridica create(@Valid PessoaJuridica pessoaJuridica) {
        repository.persist(pessoaJuridica);
        return pessoaJuridica;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public PessoaJuridica update(@PathParam("id") Long id, @Valid PessoaJuridica pessoaJuridica) {
        PessoaJuridica entity = repository.findById(id);
        entity.setNome(pessoaJuridica.getNome());
        entity.setCnpj(pessoaJuridica.getCnpj());
        repository.persist(entity);
        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        repository.deleteById(id);
    }
}
