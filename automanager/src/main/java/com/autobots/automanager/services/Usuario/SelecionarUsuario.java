package com.autobots.automanager.services.Usuario;

import com.autobots.automanager.dtos.response.UsuarioResponseDTO;
import com.autobots.automanager.exceptions.RecursoNaoEncontradoException;
import com.autobots.automanager.mappers.UsuarioMapper;
import com.autobots.automanager.model.entity.AdicionadorLinkUsuario;
import com.autobots.automanager.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SelecionarUsuario {
    @Autowired private UsuarioRepositorio usuarioRepositorio;
    @Autowired private UsuarioMapper usuarioMapper;
    @Autowired private AdicionadorLinkUsuario adicionadorLink;

    public UsuarioResponseDTO selecionarUsuario(Long id) {
        return usuarioRepositorio.findById(id)
                .map(u -> {
                    UsuarioResponseDTO dto = usuarioMapper.toDTO(u);
                    adicionadorLink.adicionarLink(dto);
                    return dto;
                })
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuario", id));
    }

    public List<UsuarioResponseDTO> listarUsuarios() {
        List<UsuarioResponseDTO> lista = usuarioRepositorio.findAll()
                .stream().map(usuarioMapper::toDTO).collect(Collectors.toList());
        adicionadorLink.adicionarLink(lista);
        return lista;
    }
}