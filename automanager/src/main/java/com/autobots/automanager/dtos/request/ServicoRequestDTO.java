package com.autobots.automanager.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ServicoRequestDTO {
    @NotBlank
    private String nome;
    @Positive
    private double valor;
    private String descricao;
}