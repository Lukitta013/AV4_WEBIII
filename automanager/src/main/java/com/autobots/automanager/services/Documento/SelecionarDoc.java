package com.autobots.automanager.services.Documento;

import com.autobots.automanager.dtos.response.DocumentoResponseDTO;
import com.autobots.automanager.exceptions.RecursoNaoEncontradoException;
import com.autobots.automanager.mappers.DocumentoMapper;
import com.autobots.automanager.model.entity.AdicionadorLinkDocumento;
import com.autobots.automanager.repository.DocumentoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SelecionarDoc {
    @Autowired private DocumentoRepositorio documentoRepositorio;
    @Autowired private DocumentoMapper documentoMapper;
    @Autowired private AdicionadorLinkDocumento adicionadorLink;

    public DocumentoResponseDTO selecionarDocumento(Long id) {
        return documentoRepositorio.findById(id)
                .map(doc -> {
                    DocumentoResponseDTO dto = documentoMapper.toDTO(doc);
                    adicionadorLink.adicionarLink(dto);
                    return dto;
                })
                .orElseThrow(() -> new RecursoNaoEncontradoException("Documento", id));
    }

    public List<DocumentoResponseDTO> listarDocumentos() {
        List<DocumentoResponseDTO> lista = documentoRepositorio.findAll()
                .stream().map(documentoMapper::toDTO).collect(Collectors.toList());
        adicionadorLink.adicionarLink(lista);
        return lista;
    }
}