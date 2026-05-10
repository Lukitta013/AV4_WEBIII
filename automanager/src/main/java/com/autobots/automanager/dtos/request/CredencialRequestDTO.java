package com.autobots.automanager.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class CredencialRequestDTO {
    @NotNull
    private Date criacao;
    private Date ultimoAcesso;
    private Boolean inativo;
}