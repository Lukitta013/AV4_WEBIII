package com.autobots.automanager.controller;

import com.autobots.automanager.dtos.request.MercadoriaRequestDTO;
import com.autobots.automanager.dtos.response.MercadoriaResponseDTO;
import com.autobots.automanager.services.Mercadoria.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mercadorias")
public class MercadoriaController {

    @Autowired private CadastrarMercadoria cadastrarMercadoria;
    @Autowired private SelecionarMercadoria selecionarMercadoria;
    @Autowired private AlterarMercadoria alterarMercadoria;
    @Autowired private ExcluirMercadoria excluirMercadoria;

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','GERENTE')")
    @PostMapping
    public ResponseEntity<MercadoriaResponseDTO> criar(@RequestBody @Valid MercadoriaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastrarMercadoria.cadastrarMercadoria(dto));
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','GERENTE','VENDEDOR','CLIENTE')")
    @GetMapping
    public ResponseEntity<List<MercadoriaResponseDTO>> listar() {
        return ResponseEntity.ok(selecionarMercadoria.listarMercadorias());
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','GERENTE','VENDEDOR','CLIENTE')")
    @GetMapping("/{id}")
    public ResponseEntity<MercadoriaResponseDTO> pegar(@PathVariable Long id) {
        return ResponseEntity.ok(selecionarMercadoria.selecionarMercadoria(id));
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','GERENTE')")
    @PutMapping("/{id}")
    public ResponseEntity<MercadoriaResponseDTO> atualizar(@PathVariable Long id,
                                                           @RequestBody @Valid MercadoriaRequestDTO dto) {
        return ResponseEntity.ok(alterarMercadoria.alterarMercadoria(id, dto));
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','GERENTE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        excluirMercadoria.excluirMercadoria(id);
        return ResponseEntity.noContent().build();
    }
}