package com.autobots.automanager.dtos.response;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class EnderecoResponseDTO extends RepresentationModel<EnderecoResponseDTO> {
    private Long id;
    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    private String numero;
    private String codigoPostal;
    private String informacoesAdicionais;
}