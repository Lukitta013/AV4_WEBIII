package com.autobots.automanager.services.Venda;

import com.autobots.automanager.exceptions.RecursoNaoEncontradoException;
import com.autobots.automanager.model.entity.Venda;
import com.autobots.automanager.repository.VendaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExcluirVenda {
    @Autowired private VendaRepositorio vendaRepositorio;

    public void excluirVenda(Long id) {
        Venda venda = vendaRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Venda", id));
        vendaRepositorio.delete(venda);
    }
}