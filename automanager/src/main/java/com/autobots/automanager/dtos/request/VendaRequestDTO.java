package com.autobots.automanager.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

@Data
public class VendaRequestDTO {
    @NotBlank
    private String identificacao;
    private Long clienteId;
    private Long funcionarioId;
    private Long veiculoId;
    private Set<Long> mercadoriaIds;
    private Set<Long> servicoIds;
}