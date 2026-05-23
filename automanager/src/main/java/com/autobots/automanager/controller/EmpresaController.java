package com.autobots.automanager.controller;

import com.autobots.automanager.dtos.request.EmpresaRequestDTO;
import com.autobots.automanager.dtos.response.EmpresaResponseDTO;
import com.autobots.automanager.services.Empresa.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired private CadastrarEmpresa cadastrarEmpresa;
    @Autowired private SelecionarEmpresa selecionarEmpresa;
    @Autowired private AlterarEmpresa alterarEmpresa;
    @Autowired private ExcluirEmpresa excluirEmpresa;

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping
    public ResponseEntity<EmpresaResponseDTO> criar(@RequestBody @Valid EmpresaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastrarEmpresa.cadastrarEmpresa(dto));
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','GERENTE')")
    @GetMapping("/{id}")
    public ResponseEntity<EmpresaResponseDTO> pegar(@PathVariable Long id) {
        return ResponseEntity.ok(selecionarEmpresa.selecionarEmpresa(id));
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','GERENTE')")
    @GetMapping
    public ResponseEntity<List<EmpresaResponseDTO>> listar() {
        return ResponseEntity.ok(selecionarEmpresa.listarEmpresas());
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PutMapping("/{id}")
    public ResponseEntity<EmpresaResponseDTO> atualizar(@PathVariable Long id,
                                                        @RequestBody @Valid EmpresaRequestDTO dto) {
        return ResponseEntity.ok(alterarEmpresa.alterarEmpresa(id, dto));
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        excluirEmpresa.excluirEmpresa(id);
        return ResponseEntity.noContent().build();
    }
}