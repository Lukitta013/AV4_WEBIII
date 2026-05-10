package com.autobots.automanager.controller;

import com.autobots.automanager.dtos.request.TelefoneRequestDTO;
import com.autobots.automanager.dtos.response.TelefoneResponseDTO;
import com.autobots.automanager.services.Telefone.AlterarTelefone;
import com.autobots.automanager.services.Telefone.CadastrarTelefone;
import com.autobots.automanager.services.Telefone.ExcluirTelefone;
import com.autobots.automanager.services.Telefone.SelecionarTelefone;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/telefones")
public class TelefoneController {

    @Autowired private CadastrarTelefone cadastrarTelefone;
    @Autowired private SelecionarTelefone selecionarTelefone;
    @Autowired private AlterarTelefone alterarTelefone;
    @Autowired private ExcluirTelefone excluirTelefone;

    @PostMapping
    public ResponseEntity<TelefoneResponseDTO> cadastrar(@RequestBody @Valid TelefoneRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastrarTelefone.cadastrarTelefone(dto));
    }

    @GetMapping
    public ResponseEntity<List<TelefoneResponseDTO>> listar() {
        return ResponseEntity.ok(selecionarTelefone.listarTelefones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TelefoneResponseDTO> pegar(@PathVariable Long id) {
        return ResponseEntity.ok(selecionarTelefone.selecionarTelefone(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TelefoneResponseDTO> atualizar(@PathVariable Long id,
                                                         @RequestBody @Valid TelefoneRequestDTO dto) {
        return ResponseEntity.ok(alterarTelefone.alterarTelefone(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        excluirTelefone.excluirTelefone(id);
        return ResponseEntity.noContent().build();
    }
}