package com.autobots.automanager.services.Documento;

import com.autobots.automanager.dtos.request.DocumentoRequestDTO;
import com.autobots.automanager.dtos.response.DocumentoResponseDTO;
import com.autobots.automanager.exceptions.RecursoNaoEncontradoException;
import com.autobots.automanager.mappers.DocumentoMapper;
import com.autobots.automanager.model.entity.Documento;
import com.autobots.automanager.repository.DocumentoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlterarDoc {
    @Autowired private DocumentoRepositorio documentoRepositorio;
    @Autowired private DocumentoMapper documentoMapper;

    public DocumentoResponseDTO alterarDocumento(Long id, DocumentoRequestDTO dto) {
        Documento documento = documentoRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Documento", id));
        documento.setTipoDocumento(dto.getTipo());
        documento.setNumero(dto.getNumero());
        return documentoMapper.toDTO(documentoRepositorio.save(documento));
    }
}