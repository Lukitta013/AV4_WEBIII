package com.autobots.automanager.model.entity;

import com.autobots.automanager.controller.VeiculoController;
import com.autobots.automanager.dtos.response.VeiculoResponseDTO;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdicionadorLinkVeiculo implements AdicionadorLink<VeiculoResponseDTO> {

    @Override
    public void adicionarLink(VeiculoResponseDTO objeto) {
        Long id = objeto.getId();

        Link selfLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(VeiculoController.class).pegar(id))
                .withSelfRel();

        Link colecaoLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(VeiculoController.class).listar())
                .withRel("veiculos");

        Link atualizarLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(VeiculoController.class)
                        .atualizar(id, null))
                .withRel("atualizar");

        Link excluirLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(VeiculoController.class).excluir(id))
                .withRel("excluir");

        objeto.add(selfLink, colecaoLink, atualizarLink, excluirLink);
    }

    @Override
    public void adicionarLink(List<VeiculoResponseDTO> lista) {
        lista.forEach(this::adicionarLink);
    }
}