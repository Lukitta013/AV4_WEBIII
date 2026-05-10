package com.autobots.automanager.services.Usuario;

import com.autobots.automanager.exceptions.RecursoNaoEncontradoException;
import com.autobots.automanager.model.entity.Usuario;
import com.autobots.automanager.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExcluirUsuario {
    @Autowired private UsuarioRepositorio usuarioRepositorio;

    public void excluirUsuario(Long id) {
        Usuario usuario = usuarioRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuario", id));
        usuarioRepositorio.delete(usuario);
    }
}