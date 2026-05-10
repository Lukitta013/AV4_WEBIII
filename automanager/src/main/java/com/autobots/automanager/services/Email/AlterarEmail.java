package com.autobots.automanager.services.Email;

import com.autobots.automanager.dtos.request.EmailRequestDTO;
import com.autobots.automanager.dtos.response.EmailResponseDTO;
import com.autobots.automanager.exceptions.RecursoNaoEncontradoException;
import com.autobots.automanager.mappers.EmailMapper;
import com.autobots.automanager.model.entity.Email;
import com.autobots.automanager.repository.EmailRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlterarEmail {
    @Autowired private EmailRepositorio emailRepositorio;
    @Autowired private EmailMapper emailMapper;

    public EmailResponseDTO alterarEmail(Long id, EmailRequestDTO dto) {
        Email email = emailRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Email", id));
        email.setEndereco(dto.getEndereco());
        return emailMapper.toDTO(emailRepositorio.save(email));
    }
}