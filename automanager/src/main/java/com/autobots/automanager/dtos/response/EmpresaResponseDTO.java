package com.autobots.automanager.dtos.response;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;
import java.util.Set;

@Data
public class EmpresaResponseDTO extends RepresentationModel<EmpresaResponseDTO> {
    private Long id;
    private String razaoSocial;
    private String nomeFantasia;
    private Date cadastro;
    private Set<TelefoneResponseDTO> telefones;
    private EnderecoResponseDTO endereco;
    private Set<Long> usuarioIds;
    private Set<Long> servicoIds;
    private Set<Long> vendaIds;
}