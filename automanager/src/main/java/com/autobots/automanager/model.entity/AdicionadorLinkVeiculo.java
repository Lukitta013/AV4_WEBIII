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
        Link selfLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(VeiculoController.class).pegar(objeto.getId()))
                .withSelfRel();
        objeto.add(selfLink);
        Link linkColecao = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(VeiculoController.class).listar())
                .withRel("veiculos");
        objeto.add(linkColecao);
    }

    @Override
    public void adicionarLink(List<VeiculoResponseDTO> lista) {
        lista.forEach(this::adicionarLink);
    }
}