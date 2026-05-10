package com.autobots.automanager.model.entity;

import com.autobots.automanager.controller.EnderecoController;
import com.autobots.automanager.dtos.response.EnderecoResponseDTO;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdicionadorLinkEndereco implements AdicionadorLink<EnderecoResponseDTO> {

    @Override
    public void adicionarLink(EnderecoResponseDTO objeto) {
        Link selfLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(EnderecoController.class).pegar(objeto.getId()))
                .withSelfRel();
        Link colecaoLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(EnderecoController.class).listar())
                .withRel("enderecos");
        objeto.add(selfLink, colecaoLink);
    }

    @Override
    public void adicionarLink(List<EnderecoResponseDTO> lista) {
        lista.forEach(this::adicionarLink);
    }
}