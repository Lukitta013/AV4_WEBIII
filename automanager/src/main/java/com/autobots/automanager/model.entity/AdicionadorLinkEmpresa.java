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
        Link selfLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(EmpresaController.class).pegar(objeto.getId()))
                .withSelfRel();
        objeto.add(selfLink);
        Link linkColecao = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(EmpresaController.class).listar())
                .withRel("empresas");
        objeto.add(linkColecao);
    }

    @Override
    public void adicionarLink(List<EmpresaResponseDTO> lista) {
        lista.forEach(this::adicionarLink);
    }
}