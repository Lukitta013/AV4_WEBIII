package com.autobots.automanager.model.entity;

import com.autobots.automanager.controller.ServicoController;
import com.autobots.automanager.dtos.response.ServicoResponseDTO;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdicionadorLinkServico implements AdicionadorLink<ServicoResponseDTO> {
    @Override
    public void adicionarLink(ServicoResponseDTO objeto) {
        Link selfLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(ServicoController.class).pegar(objeto.getId()))
                .withSelfRel();
        objeto.add(selfLink);
        Link linkColecao = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(ServicoController.class).listar())
                .withRel("servicos");
        objeto.add(linkColecao);
    }

    @Override
    public void adicionarLink(List<ServicoResponseDTO> lista) {
        lista.forEach(this::adicionarLink);
    }
}