package com.autobots.automanager.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.Date;

@Data
public class MercadoriaRequestDTO {
    @NotNull
    private Date validade;
    @NotNull
    private Date fabricacao;
    @NotBlank
    private String nome;
    @NotNull
    private Long quantidade;
    @Positive
    private double valor;
    private String descricao;
}