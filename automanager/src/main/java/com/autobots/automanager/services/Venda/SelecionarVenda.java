package com.autobots.automanager.services.Venda;

import com.autobots.automanager.dtos.response.VendaResponseDTO;
import com.autobots.automanager.exceptions.RecursoNaoEncontradoException;
import com.autobots.automanager.mappers.VendaMapper;
import com.autobots.automanager.model.entity.AdicionadorLinkVenda;
import com.autobots.automanager.repository.VendaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SelecionarVenda {
    @Autowired private VendaRepositorio vendaRepositorio;
    @Autowired private VendaMapper vendaMapper;
    @Autowired private AdicionadorLinkVenda adicionadorLink;

    public VendaResponseDTO selecionarVenda(Long id) {
        return vendaRepositorio.findById(id)
                .map(v -> {
                    VendaResponseDTO dto = vendaMapper.toDTO(v);
                    adicionadorLink.adicionarLink(dto);
                    return dto;
                })
                .orElseThrow(() -> new RecursoNaoEncontradoException("Venda", id));
    }

    public List<VendaResponseDTO> listarVendas() {
        List<VendaResponseDTO> lista = vendaRepositorio.findAll()
                .stream().map(vendaMapper::toDTO).collect(Collectors.toList());
        adicionadorLink.adicionarLink(lista);
        return lista;
    }
}