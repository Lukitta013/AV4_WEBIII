package com.autobots.automanager.services.Servico;

import com.autobots.automanager.dtos.request.ServicoRequestDTO;
import com.autobots.automanager.dtos.response.ServicoResponseDTO;
import com.autobots.automanager.mappers.ServicoMapper;
import com.autobots.automanager.repository.ServicoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastrarServico {
    @Autowired private ServicoRepositorio servicoRepositorio;
    @Autowired private ServicoMapper servicoMapper;

    public ServicoResponseDTO cadastrarServico(ServicoRequestDTO dto) {
        return servicoMapper.toDTO(servicoRepositorio.save(servicoMapper.toEntity(dto)));
    }
}