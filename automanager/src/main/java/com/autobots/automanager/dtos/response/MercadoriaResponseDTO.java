package com.autobots.automanager.dtos.response;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;

@Data
public class MercadoriaResponseDTO extends RepresentationModel<MercadoriaResponseDTO> {
    private Long id;
    private Date validade;
    private Date fabricacao;
    private Date cadastro;
    private String nome;
    private long quantidade;
    private double valor;
    private String descricao;
}