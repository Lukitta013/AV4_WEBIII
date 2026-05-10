package com.autobots.automanager.dtos.response;

import lombok.Data;

import java.util.Date;

@Data
public class CredencialResponseDTO {
    private Long id;
    private Date criacao;
    private Date ultimoAcesso;
    private boolean inativo;
}