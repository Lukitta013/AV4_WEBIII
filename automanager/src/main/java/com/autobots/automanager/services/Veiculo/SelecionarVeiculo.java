package com.autobots.automanager.services.Veiculo;

import com.autobots.automanager.dtos.response.VeiculoResponseDTO;
import com.autobots.automanager.exceptions.RecursoNaoEncontradoException;
import com.autobots.automanager.mappers.VeiculoMapper;
import com.autobots.automanager.model.entity.AdicionadorLinkVeiculo;
import com.autobots.automanager.repository.VeiculoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SelecionarVeiculo {
    @Autowired private VeiculoRepositorio veiculoRepositorio;
    @Autowired private VeiculoMapper veiculoMapper;
    @Autowired private AdicionadorLinkVeiculo adicionadorLink;

    public VeiculoResponseDTO selecionarVeiculo(Long id) {
        return veiculoRepositorio.findById(id)
                .map(v -> {
                    VeiculoResponseDTO dto = veiculoMapper.toDTO(v);
                    adicionadorLink.adicionarLink(dto);
                    return dto;
                })
                .orElseThrow(() -> new RecursoNaoEncontradoException("Veiculo", id));
    }

    public List<VeiculoResponseDTO> listarVeiculos() {
        List<VeiculoResponseDTO> lista = veiculoRepositorio.findAll()
                .stream().map(veiculoMapper::toDTO).collect(Collectors.toList());
        adicionadorLink.adicionarLink(lista);
        return lista;
    }
}