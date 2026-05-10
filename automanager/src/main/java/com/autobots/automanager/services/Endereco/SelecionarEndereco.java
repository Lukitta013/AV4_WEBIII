package com.autobots.automanager.services.Endereco;

import com.autobots.automanager.dtos.response.EnderecoResponseDTO;
import com.autobots.automanager.exceptions.RecursoNaoEncontradoException;
import com.autobots.automanager.mappers.EnderecoMapper;
import com.autobots.automanager.model.entity.AdicionadorLinkEndereco;
import com.autobots.automanager.repository.EnderecoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SelecionarEndereco {
    @Autowired private EnderecoRepositorio enderecoRepositorio;
    @Autowired private EnderecoMapper enderecoMapper;
    @Autowired private AdicionadorLinkEndereco adicionadorLink;

    public EnderecoResponseDTO selecionarEndereco(Long id) {
        return enderecoRepositorio.findById(id)
                .map(e -> {
                    EnderecoResponseDTO dto = enderecoMapper.toDTO(e);
                    adicionadorLink.adicionarLink(dto);
                    return dto;
                })
                .orElseThrow(() -> new RecursoNaoEncontradoException("Endereco", id));
    }

    public List<EnderecoResponseDTO> listarEnderecos() {
        List<EnderecoResponseDTO> lista = enderecoRepositorio.findAll()
                .stream().map(enderecoMapper::toDTO).collect(Collectors.toList());
        adicionadorLink.adicionarLink(lista);
        return lista;
    }
}