package com.autobots.automanager.services.Telefone;

import com.autobots.automanager.dtos.request.TelefoneRequestDTO;
import com.autobots.automanager.dtos.response.TelefoneResponseDTO;
import com.autobots.automanager.exceptions.RecursoNaoEncontradoException;
import com.autobots.automanager.mappers.TelefoneMapper;
import com.autobots.automanager.model.entity.Telefone;
import com.autobots.automanager.repository.TelefoneRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlterarTelefone {
    @Autowired private TelefoneRepositorio telefoneRepositorio;
    @Autowired private TelefoneMapper telefoneMapper;

    public TelefoneResponseDTO alterarTelefone(Long id, TelefoneRequestDTO dto) {
        Telefone telefone = telefoneRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Telefone", id));
        telefone.setDdd(dto.getDdd());
        telefone.setNumero(dto.getNumero());
        return telefoneMapper.toDTO(telefoneRepositorio.save(telefone));
    }
}