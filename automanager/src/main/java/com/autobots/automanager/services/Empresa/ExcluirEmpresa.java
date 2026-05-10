package com.autobots.automanager.services.Empresa;

import com.autobots.automanager.exceptions.RecursoNaoEncontradoException;
import com.autobots.automanager.model.entity.Empresa;
import com.autobots.automanager.repository.EmpresaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExcluirEmpresa {
    @Autowired private EmpresaRepositorio empresaRepositorio;

    public void excluirEmpresa(Long id) {
        Empresa empresa = empresaRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Empresa", id));
        empresaRepositorio.delete(empresa);
    }
}