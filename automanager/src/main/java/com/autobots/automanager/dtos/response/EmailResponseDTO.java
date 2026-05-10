package com.autobots.automanager.dtos.response;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class EmailResponseDTO extends RepresentationModel<EmailResponseDTO> {
    private Long id;
    private String endereco;
}