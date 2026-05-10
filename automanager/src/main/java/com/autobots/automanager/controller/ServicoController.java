package com.autobots.automanager.controller;

import com.autobots.automanager.dtos.request.ServicoRequestDTO;
import com.autobots.automanager.dtos.response.ServicoResponseDTO;
import com.autobots.automanager.services.Servico.AlterarServico;
import com.autobots.automanager.services.Servico.CadastrarServico;
import com.autobots.automanager.services.Servico.ExcluirServico;
import com.autobots.automanager.services.Servico.SelecionarServico;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired private CadastrarServico cadastrarServico;
    @Autowired private SelecionarServico selecionarServico;
    @Autowired private AlterarServico alterarServico;
    @Autowired private ExcluirServico excluirServico;

    @PostMapping
    public ResponseEntity<ServicoResponseDTO> criar(@RequestBody @Valid ServicoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastrarServico.cadastrarServico(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoResponseDTO> pegar(@PathVariable Long id) {
        return ResponseEntity.ok(selecionarServico.selecionarServico(id));
    }

    @GetMapping
    public ResponseEntity<List<ServicoResponseDTO>> listar() {
        return ResponseEntity.ok(selecionarServico.listarServicos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoResponseDTO> atualizar(@PathVariable Long id,
                                                        @RequestBody @Valid ServicoRequestDTO dto) {
        return ResponseEntity.ok(alterarServico.alterarServico(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        excluirServico.excluirServico(id);
        return ResponseEntity.noContent().build();
    }
}