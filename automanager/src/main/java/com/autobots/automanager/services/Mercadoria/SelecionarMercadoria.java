package com.autobots.automanager.services.Mercadoria;

import com.autobots.automanager.dtos.response.MercadoriaResponseDTO;
import com.autobots.automanager.exceptions.RecursoNaoEncontradoException;
import com.autobots.automanager.mappers.MercadoriaMapper;
import com.autobots.automanager.model.entity.AdicionadorLinkMercadoria;
import com.autobots.automanager.repository.MercadoriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SelecionarMercadoria {
    @Autowired private MercadoriaRepositorio mercadoriaRepositorio;
    @Autowired private MercadoriaMapper mercadoriaMapper;
    @Autowired private AdicionadorLinkMercadoria adicionadorLink;

    public MercadoriaResponseDTO selecionarMercadoria(Long id) {
        return mercadoriaRepositorio.findById(id)
                .map(m -> {
                    MercadoriaResponseDTO dto = mercadoriaMapper.toDTO(m);
                    adicionadorLink.adicionarLink(dto);
                    return dto;
                })
                .orElseThrow(() -> new RecursoNaoEncontradoException("Mercadoria", id));
    }

    public List<MercadoriaResponseDTO> listarMercadorias() {
        List<MercadoriaResponseDTO> lista = mercadoriaRepositorio.findAll()
                .stream().map(mercadoriaMapper::toDTO).collect(Collectors.toList());
        adicionadorLink.adicionarLink(lista);
        return lista;
    }
}