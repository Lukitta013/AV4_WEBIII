package com.autobots.automanager.controller;

import com.autobots.automanager.dtos.request.ServicoRequestDTO;
import com.autobots.automanager.dtos.response.ServicoResponseDTO;
import com.autobots.automanager.services.Servico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired private CadastrarServico cadastrarServico;
    @Autowired private SelecionarServico selecionarServico;
    @Autowired private AlterarServico alterarServico;
    @Autowired private ExcluirServico excluirServico;

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','GERENTE')")
    @PostMapping
    public ResponseEntity<ServicoResponseDTO> criar(@RequestBody @Valid ServicoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastrarServico.cadastrarServico(dto));
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','GERENTE','VENDEDOR','CLIENTE')")
    @GetMapping("/{id}")
    public ResponseEntity<ServicoResponseDTO> pegar(@PathVariable Long id) {
        return ResponseEntity.ok(selecionarServico.selecionarServico(id));
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','GERENTE','VENDEDOR','CLIENTE')")
    @GetMapping
    public ResponseEntity<List<ServicoResponseDTO>> listar() {
        return ResponseEntity.ok(selecionarServico.listarServicos());
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','GERENTE')")
    @PutMapping("/{id}")
    public ResponseEntity<ServicoResponseDTO> atualizar(@PathVariable Long id,
                                                        @RequestBody @Valid ServicoRequestDTO dto) {
        return ResponseEntity.ok(alterarServico.alterarServico(id, dto));
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','GERENTE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        excluirServico.excluirServico(id);
        return ResponseEntity.noContent().build();
    }
}