package com.autobots.automanager.dtos.response;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;
import java.util.Set;

@Data
public class VendaResponseDTO extends RepresentationModel<VendaResponseDTO> {
    private Long id;
    private Date cadastro;
    private String identificacao;
    private Long clienteId;
    private Long funcionarioId;
    private Long veiculoId;
    private Set<MercadoriaResponseDTO> mercadorias;
    private Set<ServicoResponseDTO> servicos;
}