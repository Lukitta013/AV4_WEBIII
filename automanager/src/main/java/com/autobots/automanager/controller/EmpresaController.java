package com.autobots.automanager.controller;

import com.autobots.automanager.dtos.request.EmpresaRequestDTO;
import com.autobots.automanager.dtos.response.EmpresaResponseDTO;
import com.autobots.automanager.services.Empresa.AlterarEmpresa;
import com.autobots.automanager.services.Empresa.CadastrarEmpresa;
import com.autobots.automanager.services.Empresa.ExcluirEmpresa;
import com.autobots.automanager.services.Empresa.SelecionarEmpresa;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired private CadastrarEmpresa cadastrarEmpresa;
    @Autowired private SelecionarEmpresa selecionarEmpresa;
    @Autowired private AlterarEmpresa alterarEmpresa;
    @Autowired private ExcluirEmpresa excluirEmpresa;

    @PostMapping
    public ResponseEntity<EmpresaResponseDTO> criar(@RequestBody @Valid EmpresaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastrarEmpresa.cadastrarEmpresa(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaResponseDTO> pegar(@PathVariable Long id) {
        return ResponseEntity.ok(selecionarEmpresa.selecionarEmpresa(id));
    }

    @GetMapping
    public ResponseEntity<List<EmpresaResponseDTO>> listar() {
        return ResponseEntity.ok(selecionarEmpresa.listarEmpresas());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaResponseDTO> atualizar(@PathVariable Long id,
                                                        @RequestBody @Valid EmpresaRequestDTO dto) {
        return ResponseEntity.ok(alterarEmpresa.alterarEmpresa(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        excluirEmpresa.excluirEmpresa(id);
        return ResponseEntity.noContent().build();
    }
}