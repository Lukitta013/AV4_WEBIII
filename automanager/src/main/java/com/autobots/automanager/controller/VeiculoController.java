package com.autobots.automanager.controller;

import com.autobots.automanager.dtos.request.VeiculoRequestDTO;
import com.autobots.automanager.dtos.response.VeiculoResponseDTO;
import com.autobots.automanager.services.Veiculo.AlterarVeiculo;
import com.autobots.automanager.services.Veiculo.CadastrarVeiculo;
import com.autobots.automanager.services.Veiculo.ExcluirVeiculo;
import com.autobots.automanager.services.Veiculo.SelecionarVeiculo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired private CadastrarVeiculo cadastrarVeiculo;
    @Autowired private SelecionarVeiculo selecionarVeiculo;
    @Autowired private AlterarVeiculo alterarVeiculo;
    @Autowired private ExcluirVeiculo excluirVeiculo;

    @PostMapping
    public ResponseEntity<VeiculoResponseDTO> criar(@RequestBody @Valid VeiculoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastrarVeiculo.cadastrarVeiculo(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoResponseDTO> pegar(@PathVariable Long id) {
        return ResponseEntity.ok(selecionarVeiculo.selecionarVeiculo(id));
    }

    @GetMapping
    public ResponseEntity<List<VeiculoResponseDTO>> listar() {
        return ResponseEntity.ok(selecionarVeiculo.listarVeiculos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeiculoResponseDTO> atualizar(@PathVariable Long id,
                                                        @RequestBody @Valid VeiculoRequestDTO dto) {
        return ResponseEntity.ok(alterarVeiculo.alterarVeiculo(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        excluirVeiculo.excluirVeiculo(id);
        return ResponseEntity.noContent().build();
    }
}