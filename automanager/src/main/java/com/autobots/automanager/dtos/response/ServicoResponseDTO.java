package com.autobots.automanager.dtos.response;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class ServicoResponseDTO extends RepresentationModel<ServicoResponseDTO> {
    private Long id;
    private String nome;
    private double valor;
    private String descricao;
}