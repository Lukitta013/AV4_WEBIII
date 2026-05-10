package com.autobots.automanager.services.Servico;

import com.autobots.automanager.dtos.response.ServicoResponseDTO;
import com.autobots.automanager.exceptions.RecursoNaoEncontradoException;
import com.autobots.automanager.mappers.ServicoMapper;
import com.autobots.automanager.model.entity.AdicionadorLinkServico;
import com.autobots.automanager.repository.ServicoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SelecionarServico {
    @Autowired private ServicoRepositorio servicoRepositorio;
    @Autowired private ServicoMapper servicoMapper;
    @Autowired private AdicionadorLinkServico adicionadorLink;

    public ServicoResponseDTO selecionarServico(Long id) {
        return servicoRepositorio.findById(id)
                .map(s -> {
                    ServicoResponseDTO dto = servicoMapper.toDTO(s);
                    adicionadorLink.adicionarLink(dto);
                    return dto;
                })
                .orElseThrow(() -> new RecursoNaoEncontradoException("Servico", id));
    }

    public List<ServicoResponseDTO> listarServicos() {
        List<ServicoResponseDTO> lista = servicoRepositorio.findAll()
                .stream().map(servicoMapper::toDTO).collect(Collectors.toList());
        adicionadorLink.adicionarLink(lista);
        return lista;
    }
}