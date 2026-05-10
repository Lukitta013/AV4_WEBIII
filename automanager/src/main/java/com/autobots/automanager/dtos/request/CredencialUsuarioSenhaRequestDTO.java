package com.autobots.automanager.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CredencialUsuarioSenhaRequestDTO {
    @NotBlank
    private String nomeUsuario;
    @NotBlank
    private String senha;
}