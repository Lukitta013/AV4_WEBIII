package com.autobots.automanager.model.entity;

import com.autobots.automanager.controller.EnderecoController;
import com.autobots.automanager.dtos.response.EnderecoResponseDTO;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdicionadorLinkEndereco implements AdicionadorLink<EnderecoResponseDTO> {

    @Override
    public void adicionarLink(EnderecoResponseDTO objeto) {
        Long id = objeto.getId();

        Link selfLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(EnderecoController.class).pegar(id))
                .withSelfRel();

        Link colecaoLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(EnderecoController.class).listar())
                .withRel("enderecos");

        Link atualizarLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(EnderecoController.class)
                        .atualizar(id, null))
                .withRel("atualizar");

        Link excluirLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(EnderecoController.class).excluir(id))
                .withRel("excluir");

        objeto.add(selfLink, colecaoLink, atualizarLink, excluirLink);
    }

    @Override
    public void adicionarLink(List<EnderecoResponseDTO> lista) {
        lista.forEach(this::adicionarLink);
    }
}