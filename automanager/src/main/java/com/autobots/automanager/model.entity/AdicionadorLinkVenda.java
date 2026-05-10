package com.autobots.automanager.model.entity;

import com.autobots.automanager.controller.VendaController;
import com.autobots.automanager.dtos.response.VendaResponseDTO;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdicionadorLinkVenda implements AdicionadorLink<VendaResponseDTO> {
    @Override
    public void adicionarLink(VendaResponseDTO objeto) {
        Link selfLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(VendaController.class).pegar(objeto.getId()))
                .withSelfRel();
        objeto.add(selfLink);
        Link linkColecao = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(VendaController.class).listar())
                .withRel("vendas");
        objeto.add(linkColecao);
    }

    @Override
    public void adicionarLink(List<VendaResponseDTO> lista) {
        lista.forEach(this::adicionarLink);
    }
}