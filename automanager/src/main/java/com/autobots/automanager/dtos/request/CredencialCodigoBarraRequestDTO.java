package com.autobots.automanager.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CredencialCodigoBarraRequestDTO {
    @NotNull
    private Long codigo;
}