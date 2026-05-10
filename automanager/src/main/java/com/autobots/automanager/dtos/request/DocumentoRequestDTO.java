package com.autobots.automanager.dtos.request;

import com.autobots.automanager.enums.TipoDocumento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DocumentoRequestDTO {
    @NotNull
    private TipoDocumento tipo;
    @NotBlank
    private String numero;
}