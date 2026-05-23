package com.autobots.automanager.controller;

import com.autobots.automanager.dtos.request.VeiculoRequestDTO;
import com.autobots.automanager.dtos.response.VeiculoResponseDTO;
import com.autobots.automanager.services.Veiculo.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired private CadastrarVeiculo cadastrarVeiculo;
    @Autowired private SelecionarVeiculo selecionarVeiculo;
    @Autowired private AlterarVeiculo alterarVeiculo;
    @Autowired private ExcluirVeiculo excluirVeiculo;

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','GERENTE','VENDEDOR')")
    @PostMapping
    public ResponseEntity<VeiculoResponseDTO> criar(@RequestBody @Valid VeiculoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastrarVeiculo.cadastrarVeiculo(dto));
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','GERENTE','VENDEDOR','CLIENTE')")
    @GetMapping("/{id}")
    public ResponseEntity<VeiculoResponseDTO> pegar(@PathVariable Long id) {
        return ResponseEntity.ok(selecionarVeiculo.selecionarVeiculo(id));
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','GERENTE','VENDEDOR')")
    @GetMapping
    public ResponseEntity<List<VeiculoResponseDTO>> listar() {
        return ResponseEntity.ok(selecionarVeiculo.listarVeiculos());
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','GERENTE')")
    @PutMapping("/{id}")
    public ResponseEntity<VeiculoResponseDTO> atualizar(@PathVariable Long id,
                                                        @RequestBody @Valid VeiculoRequestDTO dto) {
        return ResponseEntity.ok(alterarVeiculo.alterarVeiculo(id, dto));
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','GERENTE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        excluirVeiculo.excluirVeiculo(id);
        return ResponseEntity.noContent().build();
    }
}