package com.autobots.automanager.controller;

import com.autobots.automanager.dtos.request.DocumentoRequestDTO;
import com.autobots.automanager.dtos.response.DocumentoResponseDTO;
import com.autobots.automanager.services.Documento.AlterarDoc;
import com.autobots.automanager.services.Documento.CadastrarDoc;
import com.autobots.automanager.services.Documento.ExcluirDoc;
import com.autobots.automanager.services.Documento.SelecionarDoc;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documentos")
public class DocumentoController {

    @Autowired private CadastrarDoc cadastrarDoc;
    @Autowired private SelecionarDoc selecionarDoc;
    @Autowired private AlterarDoc alterarDoc;
    @Autowired private ExcluirDoc excluirDoc;

    @PostMapping
    public ResponseEntity<DocumentoResponseDTO> cadastrar(@RequestBody @Valid DocumentoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastrarDoc.criarDocumento(dto));
    }

    @GetMapping
    public ResponseEntity<List<DocumentoResponseDTO>> listar() {
        return ResponseEntity.ok(selecionarDoc.listarDocumentos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentoResponseDTO> pegar(@PathVariable Long id) {
        return ResponseEntity.ok(selecionarDoc.selecionarDocumento(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentoResponseDTO> atualizar(@PathVariable Long id,
                                                          @RequestBody @Valid DocumentoRequestDTO dto) {
        return ResponseEntity.ok(alterarDoc.alterarDocumento(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        excluirDoc.excluirDocumento(id);
        return ResponseEntity.noContent().build();
    }
}