package com.autobots.automanager.model.entity;

import com.autobots.automanager.controller.EmailController;
import com.autobots.automanager.dtos.response.EmailResponseDTO;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdicionadorLinkEmail implements AdicionadorLink<EmailResponseDTO> {

    @Override
    public void adicionarLink(EmailResponseDTO objeto) {
        Link selfLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(EmailController.class).pegar(objeto.getId()))
                .withSelfRel();
        Link colecaoLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(EmailController.class).listar())
                .withRel("emails");
        objeto.add(selfLink, colecaoLink);
    }

    @Override
    public void adicionarLink(List<EmailResponseDTO> lista) {
        lista.forEach(this::adicionarLink);
    }
}