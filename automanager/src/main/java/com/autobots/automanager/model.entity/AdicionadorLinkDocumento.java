package com.autobots.automanager.model.entity;

import com.autobots.automanager.controller.DocumentoController;
import com.autobots.automanager.dtos.response.DocumentoResponseDTO;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdicionadorLinkDocumento implements AdicionadorLink<DocumentoResponseDTO> {

    @Override
    public void adicionarLink(DocumentoResponseDTO objeto) {
        Link selfLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(DocumentoController.class).pegar(objeto.getId()))
                .withSelfRel();
        Link colecaoLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(DocumentoController.class).listar())
                .withRel("documentos");
        objeto.add(selfLink, colecaoLink);
    }

    @Override
    public void adicionarLink(List<DocumentoResponseDTO> lista) {
        lista.forEach(this::adicionarLink);
    }
}