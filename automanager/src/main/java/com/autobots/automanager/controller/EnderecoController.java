package com.autobots.automanager.controller;

import com.autobots.automanager.dtos.request.EnderecoRequestDTO;
import com.autobots.automanager.dtos.response.EnderecoResponseDTO;
import com.autobots.automanager.services.Endereco.AlterarEndereco;
import com.autobots.automanager.services.Endereco.CadastrarEndereco;
import com.autobots.automanager.services.Endereco.ExcluirEndereco;
import com.autobots.automanager.services.Endereco.SelecionarEndereco;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired private CadastrarEndereco cadastrarEndereco;
    @Autowired private SelecionarEndereco selecionarEndereco;
    @Autowired private AlterarEndereco alterarEndereco;
    @Autowired private ExcluirEndereco excluirEndereco;

    @PostMapping
    public ResponseEntity<EnderecoResponseDTO> criar(@RequestBody @Valid EnderecoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastrarEndereco.cadastrarEndereco(dto));
    }

    @GetMapping
    public ResponseEntity<List<EnderecoResponseDTO>> listar() {
        return ResponseEntity.ok(selecionarEndereco.listarEnderecos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoResponseDTO> pegar(@PathVariable Long id) {
        return ResponseEntity.ok(selecionarEndereco.selecionarEndereco(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoResponseDTO> atualizar(@PathVariable Long id,
                                                         @RequestBody @Valid EnderecoRequestDTO dto) {
        return ResponseEntity.ok(alterarEndereco.alterarEndereco(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        excluirEndereco.excluirEndereco(id);
        return ResponseEntity.noContent().build();
    }
}