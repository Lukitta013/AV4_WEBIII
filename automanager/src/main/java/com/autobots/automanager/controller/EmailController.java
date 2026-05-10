package com.autobots.automanager.controller;

import com.autobots.automanager.dtos.request.EmailRequestDTO;
import com.autobots.automanager.dtos.response.EmailResponseDTO;
import com.autobots.automanager.services.Email.AlterarEmail;
import com.autobots.automanager.services.Email.CadastrarEmail;
import com.autobots.automanager.services.Email.ExcluirEmail;
import com.autobots.automanager.services.Email.SelecionarEmail;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emails")
public class EmailController {

    @Autowired private CadastrarEmail cadastrarEmail;
    @Autowired private SelecionarEmail selecionarEmail;
    @Autowired private AlterarEmail alterarEmail;
    @Autowired private ExcluirEmail excluirEmail;

    @PostMapping
    public ResponseEntity<EmailResponseDTO> criar(@RequestBody @Valid EmailRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastrarEmail.cadastrarEmail(dto));
    }

    @GetMapping
    public ResponseEntity<List<EmailResponseDTO>> listar() {
        return ResponseEntity.ok(selecionarEmail.listarEmails());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmailResponseDTO> pegar(@PathVariable Long id) {
        return ResponseEntity.ok(selecionarEmail.selecionarEmail(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmailResponseDTO> atualizar(@PathVariable Long id,
                                                      @RequestBody @Valid EmailRequestDTO dto) {
        return ResponseEntity.ok(alterarEmail.alterarEmail(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        excluirEmail.excluirEmail(id);
        return ResponseEntity.noContent().build();
    }
}