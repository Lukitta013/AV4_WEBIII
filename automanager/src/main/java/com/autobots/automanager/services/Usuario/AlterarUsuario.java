package com.autobots.automanager.services.Usuario;

import com.autobots.automanager.dtos.request.UsuarioRequestDTO;
import com.autobots.automanager.dtos.response.UsuarioResponseDTO;
import com.autobots.automanager.exceptions.RecursoNaoEncontradoException;
import com.autobots.automanager.mappers.UsuarioMapper;
import com.autobots.automanager.model.entity.Usuario;
import com.autobots.automanager.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlterarUsuario {
    @Autowired private UsuarioRepositorio usuarioRepositorio;
    @Autowired private UsuarioMapper usuarioMapper;

    public UsuarioResponseDTO alterarUsuario(Long id, UsuarioRequestDTO dto) {
        Usuario usuario = usuarioRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuario", id));
        usuario.setNome(dto.getNome());
        usuario.setNomeSocial(dto.getNomeSocial());
        if (dto.getPerfis() != null) usuario.setPerfis(dto.getPerfis());
        return usuarioMapper.toDTO(usuarioRepositorio.save(usuario));
    }
}