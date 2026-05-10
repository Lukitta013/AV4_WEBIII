package com.autobots.automanager.services.Email;

import com.autobots.automanager.exceptions.RecursoNaoEncontradoException;
import com.autobots.automanager.model.entity.Email;
import com.autobots.automanager.repository.EmailRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExcluirEmail {
    @Autowired private EmailRepositorio emailRepositorio;

    public void excluirEmail(Long id) {
        Email email = emailRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Email", id));
        emailRepositorio.delete(email);
    }
}