package com.autobots.automanager.dtos.request;

import com.autobots.automanager.enums.TipoVeiculo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VeiculoRequestDTO {
    @NotNull
    private TipoVeiculo tipo;
    @NotBlank
    private String modelo;
    @NotBlank
    private String placa;
    private Long proprietarioId;
}