package com.autobots.automanager.services.Endereco;

import com.autobots.automanager.exceptions.RecursoNaoEncontradoException;
import com.autobots.automanager.model.entity.Endereco;
import com.autobots.automanager.repository.EnderecoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExcluirEndereco {
    @Autowired private EnderecoRepositorio enderecoRepositorio;

    public void excluirEndereco(Long id) {
        Endereco endereco = enderecoRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Endereco", id));
        enderecoRepositorio.delete(endereco);
    }
}