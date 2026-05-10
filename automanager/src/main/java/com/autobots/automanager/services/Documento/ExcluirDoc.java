package com.autobots.automanager.services.Documento;

import com.autobots.automanager.exceptions.RecursoNaoEncontradoException;
import com.autobots.automanager.model.entity.Documento;
import com.autobots.automanager.repository.DocumentoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExcluirDoc {
    @Autowired private DocumentoRepositorio documentoRepositorio;

    public void excluirDocumento(Long id) {
        Documento documento = documentoRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Documento", id));
        documentoRepositorio.delete(documento);
    }
}