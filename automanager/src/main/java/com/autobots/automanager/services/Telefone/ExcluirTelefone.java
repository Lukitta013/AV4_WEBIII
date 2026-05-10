package com.autobots.automanager.services.Telefone;

import com.autobots.automanager.exceptions.RecursoNaoEncontradoException;
import com.autobots.automanager.model.entity.Telefone;
import com.autobots.automanager.repository.TelefoneRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExcluirTelefone {
    @Autowired private TelefoneRepositorio telefoneRepositorio;

    public void excluirTelefone(Long id) {
        Telefone telefone = telefoneRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Telefone", id));
        telefoneRepositorio.delete(telefone);
    }
}