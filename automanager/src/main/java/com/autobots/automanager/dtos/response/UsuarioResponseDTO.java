package com.autobots.automanager.dtos.response;

import com.autobots.automanager.enums.PerfilUsuario;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.Set;

@Data
public class UsuarioResponseDTO extends RepresentationModel<UsuarioResponseDTO> {
    private Long id;
    private String nome;
    private String nomeSocial;
    private Set<PerfilUsuario> perfis;
    private Set<TelefoneResponseDTO> telefones;
    private EnderecoResponseDTO endereco;
    private Set<DocumentoResponseDTO> documentos;
    private Set<EmailResponseDTO> emails;
}