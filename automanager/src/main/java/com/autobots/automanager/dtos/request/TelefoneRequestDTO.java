package com.autobots.automanager.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TelefoneRequestDTO {
    @NotBlank
    private String ddd;
    @NotBlank
    private String numero;
}