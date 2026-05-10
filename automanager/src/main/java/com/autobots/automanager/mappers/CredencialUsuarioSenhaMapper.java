package com.autobots.automanager.mappers;

import com.autobots.automanager.dtos.request.CredencialUsuarioSenhaRequestDTO;
import com.autobots.automanager.dtos.response.CredencialUsuarioSenhaResponseDTO;
import com.autobots.automanager.model.entity.CredencialUsuarioSenha;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CredencialUsuarioSenhaMapper {
    CredencialUsuarioSenhaResponseDTO toDTO(CredencialUsuarioSenha credencialUsuarioSenha);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "criacao", ignore = true)
    @Mapping(target = "ultimoAcesso", ignore = true)
    @Mapping(target = "inativo", ignore = true)
    CredencialUsuarioSenha toEntity(CredencialUsuarioSenhaRequestDTO dto);
}