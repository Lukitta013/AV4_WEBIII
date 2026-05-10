package com.autobots.automanager.model.entity;

import com.autobots.automanager.controller.UsuarioController;
import com.autobots.automanager.dtos.response.UsuarioResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdicionadorLinkUsuario implements AdicionadorLink<UsuarioResponseDTO> {

    @Autowired private AdicionadorLinkDocumento adicionadorLinkDocumento;
    @Autowired private AdicionadorLinkEndereco adicionadorLinkEndereco;
    @Autowired private AdicionadorLinkTelefone adicionadorLinkTelefone;
    @Autowired private AdicionadorLinkEmail adicionadorLinkEmail;

    @Override
    public void adicionarLink(UsuarioResponseDTO objeto) {
        Link selfLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).pegar(objeto.getId()))
                .withSelfRel();
        Link colecaoLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).listar())
                .withRel("usuarios");
        objeto.add(selfLink, colecaoLink);
        if (objeto.getDocumentos() != null)
            adicionadorLinkDocumento.adicionarLink(objeto.getDocumentos().stream().toList());
        if (objeto.getEndereco() != null)
            adicionadorLinkEndereco.adicionarLink(objeto.getEndereco());
        if (objeto.getTelefones() != null)
            adicionadorLinkTelefone.adicionarLink(objeto.getTelefones().stream().toList());
        if (objeto.getEmails() != null)
            adicionadorLinkEmail.adicionarLink(objeto.getEmails().stream().toList());
    }

    @Override
    public void adicionarLink(List<UsuarioResponseDTO> lista) {
        lista.forEach(this::adicionarLink);
    }
}