package com.autobots.automanager.dtos.response;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class TelefoneResponseDTO extends RepresentationModel<TelefoneResponseDTO> {
    private Long id;
    private String ddd;
    private String numero;
}