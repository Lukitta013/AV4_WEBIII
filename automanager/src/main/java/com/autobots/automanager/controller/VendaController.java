package com.autobots.automanager.controller;

import com.autobots.automanager.dtos.request.VendaRequestDTO;
import com.autobots.automanager.dtos.response.VendaResponseDTO;
import com.autobots.automanager.services.Venda.AlterarVenda;
import com.autobots.automanager.services.Venda.CadastrarVenda;
import com.autobots.automanager.services.Venda.ExcluirVenda;
import com.autobots.automanager.services.Venda.SelecionarVenda;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired private CadastrarVenda cadastrarVenda;
    @Autowired private SelecionarVenda selecionarVenda;
    @Autowired private AlterarVenda alterarVenda;
    @Autowired private ExcluirVenda excluirVenda;

    @PostMapping
    public ResponseEntity<VendaResponseDTO> criar(@RequestBody @Valid VendaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastrarVenda.cadastrarVenda(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendaResponseDTO> pegar(@PathVariable Long id) {
        return ResponseEntity.ok(selecionarVenda.selecionarVenda(id));
    }

    @GetMapping
    public ResponseEntity<List<VendaResponseDTO>> listar() {
        return ResponseEntity.ok(selecionarVenda.listarVendas());
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendaResponseDTO> atualizar(@PathVariable Long id,
                                                      @RequestBody @Valid VendaRequestDTO dto) {
        return ResponseEntity.ok(alterarVenda.alterarVenda(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        excluirVenda.excluirVenda(id);
        return ResponseEntity.noContent().build();
    }
}