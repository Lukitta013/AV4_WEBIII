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
        Link selfLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(MercadoriaController.class).pegar(objeto.getId()))
                .withSelfRel();
        objeto.add(selfLink);
        Link linkColecao = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(MercadoriaController.class).listar())
                .withRel("mercadorias");
        objeto.add(linkColecao);
    }

    @Override
    public void adicionarLink(List<MercadoriaResponseDTO> lista) {
        lista.forEach(this::adicionarLink);
    }
}