package com.autobots.automanager.services.Telefone;

import com.autobots.automanager.dtos.response.TelefoneResponseDTO;
import com.autobots.automanager.exceptions.RecursoNaoEncontradoException;
import com.autobots.automanager.mappers.TelefoneMapper;
import com.autobots.automanager.model.entity.AdicionadorLinkTelefone;
import com.autobots.automanager.repository.TelefoneRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SelecionarTelefone {
    @Autowired private TelefoneRepositorio telefoneRepositorio;
    @Autowired private TelefoneMapper telefoneMapper;
    @Autowired private AdicionadorLinkTelefone adicionadorLink;

    public TelefoneResponseDTO selecionarTelefone(Long id) {
        return telefoneRepositorio.findById(id)
                .map(t -> {
                    TelefoneResponseDTO dto = telefoneMapper.toDTO(t);
                    adicionadorLink.adicionarLink(dto);
                    return dto;
                })
                .orElseThrow(() -> new RecursoNaoEncontradoException("Telefone", id));
    }

    public List<TelefoneResponseDTO> listarTelefones() {
        List<TelefoneResponseDTO> lista = telefoneRepositorio.findAll()
                .stream().map(telefoneMapper::toDTO).collect(Collectors.toList());
        adicionadorLink.adicionarLink(lista);
        return lista;
    }
}