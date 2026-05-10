package com.autobots.automanager.services.Servico;

import com.autobots.automanager.dtos.request.ServicoRequestDTO;
import com.autobots.automanager.dtos.response.ServicoResponseDTO;
import com.autobots.automanager.exceptions.RecursoNaoEncontradoException;
import com.autobots.automanager.mappers.ServicoMapper;
import com.autobots.automanager.model.entity.Servico;
import com.autobots.automanager.repository.ServicoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlterarServico {
    @Autowired private ServicoRepositorio servicoRepositorio;
    @Autowired private ServicoMapper servicoMapper;

    public ServicoResponseDTO alterarServico(Long id, ServicoRequestDTO dto) {
        Servico servico = servicoRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Servico", id));
        servico.setNome(dto.getNome());
        servico.setValor(dto.getValor());
        servico.setDescricao(dto.getDescricao());
        return servicoMapper.toDTO(servicoRepositorio.save(servico));
    }
}