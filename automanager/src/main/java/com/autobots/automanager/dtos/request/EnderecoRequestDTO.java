package com.autobots.automanager.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EnderecoRequestDTO {
    @NotBlank
    private String estado;
    @NotBlank
    private String cidade;
    @NotBlank
    private String bairro;
    @NotBlank
    private String rua;
    @NotBlank
    private String numero;
    @NotBlank
    private String codigoPostal;
    private String informacoesAdicionais;
}