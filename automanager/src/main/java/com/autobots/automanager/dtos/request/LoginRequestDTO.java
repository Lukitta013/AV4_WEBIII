package com.autobots.automanager.dtos.request;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String nomeUsuario;
    private String senha;
}