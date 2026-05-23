package com.autobots.automanager.dtos.request;

import com.autobots.automanager.enums.PerfilUsuario;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

@Data
public class UsuarioRequestDTO {

    @NotBlank
    private String nome;
    private String nomeSocial;
    private Set<PerfilUsuario> perfis;
    private String nomeUsuario;
    private String senha;
}