package com.autobots.automanager.model.entity;

import com.autobots.automanager.controller.TelefoneController;
import com.autobots.automanager.dtos.response.TelefoneResponseDTO;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdicionadorLinkTelefone implements AdicionadorLink<TelefoneResponseDTO> {

    @Override
    public void adicionarLink(TelefoneResponseDTO objeto) {
        Link selfLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(TelefoneController.class).pegar(objeto.getId()))
                .withSelfRel();
        Link colecaoLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(TelefoneController.class).listar())
                .withRel("telefones");
        objeto.add(selfLink, colecaoLink);
    }

    @Override
    public void adicionarLink(List<TelefoneResponseDTO> lista) {
        lista.forEach(this::adicionarLink);
    }
}