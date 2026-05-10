package com.autobots.automanager.services.Email;

import com.autobots.automanager.dtos.request.EmailRequestDTO;
import com.autobots.automanager.dtos.response.EmailResponseDTO;
import com.autobots.automanager.mappers.EmailMapper;
import com.autobots.automanager.repository.EmailRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastrarEmail {
    @Autowired private EmailRepositorio emailRepositorio;
    @Autowired private EmailMapper emailMapper;

    public EmailResponseDTO cadastrarEmail(EmailRequestDTO dto) {
        return emailMapper.toDTO(emailRepositorio.save(emailMapper.toEntity(dto)));
    }
}