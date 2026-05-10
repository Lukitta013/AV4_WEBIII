package com.autobots.automanager.services.Mercadoria;

import com.autobots.automanager.exceptions.RecursoNaoEncontradoException;
import com.autobots.automanager.model.entity.Mercadoria;
import com.autobots.automanager.repository.MercadoriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExcluirMercadoria {
    @Autowired private MercadoriaRepositorio mercadoriaRepositorio;

    public void excluirMercadoria(Long id) {
        Mercadoria mercadoria = mercadoriaRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Mercadoria", id));
        mercadoriaRepositorio.delete(mercadoria);
    }
}