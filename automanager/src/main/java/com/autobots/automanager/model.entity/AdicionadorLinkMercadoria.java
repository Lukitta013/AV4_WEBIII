package com.autobots.automanager.model.entity;

import com.autobots.automanager.controller.MercadoriaController;
import com.autobots.automanager.dtos.response.MercadoriaResponseDTO;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdicionadorLinkMercadoria implements AdicionadorLink<MercadoriaResponseDTO> {

    @Override
    public void adicionarLink(MercadoriaResponseDTO objeto) {
        Long id = objeto.getId();

        Link selfLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(MercadoriaController.class).pegar(id))
                .withSelfRel();

        Link colecaoLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(MercadoriaController.class).listar())
                .withRel("mercadorias");

        Link atualizarLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(MercadoriaController.class)
                        .atualizar(id, null))
                .withRel("atualizar");

        Link excluirLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(MercadoriaController.class).excluir(id))
                .withRel("excluir");

        objeto.add(selfLink, colecaoLink, atualizarLink, excluirLink);
    }

    @Override
    public void adicionarLink(List<MercadoriaResponseDTO> lista) {
        lista.forEach(this::adicionarLink);
    }
}