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
        Long id = objeto.getId();

        Link selfLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(DocumentoController.class).pegar(id))
                .withSelfRel();

        Link colecaoLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(DocumentoController.class).listar())
                .withRel("documentos");

        Link atualizarLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(DocumentoController.class)
                        .atualizar(id, null))
                .withRel("atualizar");

        Link excluirLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(DocumentoController.class).excluir(id))
                .withRel("excluir");

        objeto.add(selfLink, colecaoLink, atualizarLink, excluirLink);
    }

    @Override
    public void adicionarLink(List<DocumentoResponseDTO> lista) {
        lista.forEach(this::adicionarLink);
    }
}