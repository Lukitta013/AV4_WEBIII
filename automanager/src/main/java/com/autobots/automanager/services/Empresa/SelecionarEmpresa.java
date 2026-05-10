package com.autobots.automanager.services.Empresa;

import com.autobots.automanager.dtos.response.EmpresaResponseDTO;
import com.autobots.automanager.exceptions.RecursoNaoEncontradoException;
import com.autobots.automanager.mappers.EmpresaMapper;
import com.autobots.automanager.model.entity.AdicionadorLinkEmpresa;
import com.autobots.automanager.model.entity.Empresa;
import com.autobots.automanager.repository.EmpresaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SelecionarEmpresa {
    @Autowired private EmpresaRepositorio empresaRepositorio;
    @Autowired private EmpresaMapper empresaMapper;
    @Autowired private AdicionadorLinkEmpresa adicionadorLink;

    public EmpresaResponseDTO selecionarEmpresa(Long id) {
        return empresaRepositorio.findById(id)
                .map(e -> {
                    EmpresaResponseDTO dto = empresaMapper.toDTO(e);
                    dto.setUsuarioIds(e.getUsuarios().stream().map(u -> u.getId()).collect(java.util.stream.Collectors.toSet()));
                    dto.setServicoIds(e.getServicos().stream().map(s -> s.getId()).collect(java.util.stream.Collectors.toSet()));
                    dto.setVendaIds(e.getVendas().stream().map(v -> v.getId()).collect(java.util.stream.Collectors.toSet()));
                    adicionadorLink.adicionarLink(dto);
                    return dto;
                })
                .orElseThrow(() -> new RecursoNaoEncontradoException("Empresa", id));
    }

    public List<EmpresaResponseDTO> listarEmpresas() {
        List<EmpresaResponseDTO> lista = empresaRepositorio.findAll().stream()
                .map(e -> {
                    EmpresaResponseDTO dto = empresaMapper.toDTO(e);
                    dto.setUsuarioIds(e.getUsuarios().stream().map(u -> u.getId()).collect(java.util.stream.Collectors.toSet()));
                    dto.setServicoIds(e.getServicos().stream().map(s -> s.getId()).collect(java.util.stream.Collectors.toSet()));
                    dto.setVendaIds(e.getVendas().stream().map(v -> v.getId()).collect(java.util.stream.Collectors.toSet()));
                    return dto;
                })
                .collect(Collectors.toList());
        adicionadorLink.adicionarLink(lista);
        return lista;
    }
}