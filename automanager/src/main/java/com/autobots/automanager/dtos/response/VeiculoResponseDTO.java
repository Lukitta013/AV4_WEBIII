package com.autobots.automanager.dtos.response;

import com.autobots.automanager.enums.TipoVeiculo;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class VeiculoResponseDTO extends RepresentationModel<VeiculoResponseDTO> {
    private Long id;
    private TipoVeiculo tipo;
    private String modelo;
    private String placa;
    private Long proprietarioId;
}