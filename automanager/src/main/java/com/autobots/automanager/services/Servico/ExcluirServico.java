package com.autobots.automanager.services.Servico;

import com.autobots.automanager.exceptions.RecursoNaoEncontradoException;
import com.autobots.automanager.model.entity.Servico;
import com.autobots.automanager.repository.ServicoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExcluirServico {
    @Autowired private ServicoRepositorio servicoRepositorio;

    public void excluirServico(Long id) {
        Servico servico = servicoRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Servico", id));
        servicoRepositorio.delete(servico);
    }
}