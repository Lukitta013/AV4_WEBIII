package com.autobots.automanager.services.Endereco;

import com.autobots.automanager.dtos.request.EnderecoRequestDTO;
import com.autobots.automanager.dtos.response.EnderecoResponseDTO;
import com.autobots.automanager.mappers.EnderecoMapper;
import com.autobots.automanager.repository.EnderecoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastrarEndereco {
    @Autowired private EnderecoRepositorio enderecoRepositorio;
    @Autowired private EnderecoMapper enderecoMapper;

    public EnderecoResponseDTO cadastrarEndereco(EnderecoRequestDTO dto) {
        return enderecoMapper.toDTO(enderecoRepositorio.save(enderecoMapper.toEntity(dto)));
    }
}