package com.autobots.automanager.controller;

import com.autobots.automanager.dtos.request.UsuarioRequestDTO;
import com.autobots.automanager.dtos.response.UsuarioResponseDTO;
import com.autobots.automanager.services.Usuario.AlterarUsuario;
import com.autobots.automanager.services.Usuario.CadastrarUsuario;
import com.autobots.automanager.services.Usuario.ExcluirUsuario;
import com.autobots.automanager.services.Usuario.SelecionarUsuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired private CadastrarUsuario cadastrarUsuario;
    @Autowired private SelecionarUsuario selecionarUsuario;
    @Autowired private AlterarUsuario alterarUsuario;
    @Autowired private ExcluirUsuario excluirUsuario;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criar(@RequestBody @Valid UsuarioRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastrarUsuario.cadastrarUsuario(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> pegar(@PathVariable Long id) {
        return ResponseEntity.ok(selecionarUsuario.selecionarUsuario(id));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listar() {
        return ResponseEntity.ok(selecionarUsuario.listarUsuarios());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizar(@PathVariable Long id,
                                                        @RequestBody @Valid UsuarioRequestDTO dto) {
        return ResponseEntity.ok(alterarUsuario.alterarUsuario(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        excluirUsuario.excluirUsuario(id);
        return ResponseEntity.noContent().build();
    }
}