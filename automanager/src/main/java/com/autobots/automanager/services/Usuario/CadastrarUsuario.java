package com.autobots.automanager.services.Usuario;

import com.autobots.automanager.dtos.request.UsuarioRequestDTO;
import com.autobots.automanager.dtos.response.UsuarioResponseDTO;
import com.autobots.automanager.mappers.UsuarioMapper;
import com.autobots.automanager.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastrarUsuario {
    @Autowired private UsuarioRepositorio usuarioRepositorio;
    @Autowired private UsuarioMapper usuarioMapper;

    public UsuarioResponseDTO cadastrarUsuario(UsuarioRequestDTO dto) {
        return usuarioMapper.toDTO(usuarioRepositorio.save(usuarioMapper.toEntity(dto)));
    }
}