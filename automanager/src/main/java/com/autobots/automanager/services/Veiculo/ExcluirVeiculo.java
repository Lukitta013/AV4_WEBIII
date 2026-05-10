package com.autobots.automanager.services.Veiculo;

import com.autobots.automanager.exceptions.RecursoNaoEncontradoException;
import com.autobots.automanager.model.entity.Veiculo;
import com.autobots.automanager.repository.VeiculoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExcluirVeiculo {
    @Autowired private VeiculoRepositorio veiculoRepositorio;

    public void excluirVeiculo(Long id) {
        Veiculo veiculo = veiculoRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Veiculo", id));
        veiculoRepositorio.delete(veiculo);
    }
}