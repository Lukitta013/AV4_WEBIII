package com.autobots.automanager.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmpresaRequestDTO {
    @NotBlank
    private String razaoSocial;
    @NotBlank
    private String nomeFantasia;
}