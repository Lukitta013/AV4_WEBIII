package com.autobots.automanager.services.Email;

import com.autobots.automanager.dtos.response.EmailResponseDTO;
import com.autobots.automanager.exceptions.RecursoNaoEncontradoException;
import com.autobots.automanager.mappers.EmailMapper;
import com.autobots.automanager.model.entity.AdicionadorLinkEmail;
import com.autobots.automanager.repository.EmailRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SelecionarEmail {
    @Autowired private EmailRepositorio emailRepositorio;
    @Autowired private EmailMapper emailMapper;
    @Autowired private AdicionadorLinkEmail adicionadorLink;

    public EmailResponseDTO selecionarEmail(Long id) {
        return emailRepositorio.findById(id)
                .map(e -> {
                    EmailResponseDTO dto = emailMapper.toDTO(e);
                    adicionadorLink.adicionarLink(dto);
                    return dto;
                })
                .orElseThrow(() -> new RecursoNaoEncontradoException("Email", id));
    }

    public List<EmailResponseDTO> listarEmails() {
        List<EmailResponseDTO> lista = emailRepositorio.findAll()
                .stream().map(emailMapper::toDTO).collect(Collectors.toList());
        adicionadorLink.adicionarLink(lista);
        return lista;
    }
}