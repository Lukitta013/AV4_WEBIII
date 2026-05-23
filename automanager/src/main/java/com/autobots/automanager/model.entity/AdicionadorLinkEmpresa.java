package com.autobots.automanager.model.entity;

import com.autobots.automanager.controller.EmpresaController;
import com.autobots.automanager.dtos.response.EmpresaResponseDTO;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdicionadorLinkEmpresa implements AdicionadorLink<EmpresaResponseDTO> {

    @Override
    public void adicionarLink(EmpresaResponseDTO objeto) {
        Long id = objeto.getId();

        Link selfLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(EmpresaController.class).pegar(id))
                .withSelfRel();

        Link colecaoLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(EmpresaController.class).listar())
                .withRel("empresas");

        Link atualizarLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(EmpresaController.class)
                        .atualizar(id, null))
                .withRel("atualizar");

        Link excluirLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(EmpresaController.class).excluir(id))
                .withRel("excluir");

        objeto.add(selfLink, colecaoLink, atualizarLink, excluirLink);
    }

    @Override
    public void adicionarLink(List<EmpresaResponseDTO> lista) {
        lista.forEach(this::adicionarLink);
    }
}