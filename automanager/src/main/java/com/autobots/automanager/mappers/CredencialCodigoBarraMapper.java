package com.autobots.automanager.mappers;

import com.autobots.automanager.dtos.request.CredencialCodigoBarraRequestDTO;
import com.autobots.automanager.dtos.response.CredencialCodigoBarraResponseDTO;
import com.autobots.automanager.model.entity.CredencialCodigoBarra;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CredencialCodigoBarraMapper {
    CredencialCodigoBarraResponseDTO toDTO(CredencialCodigoBarra credencialCodigoBarra);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "criacao", ignore = true)
    @Mapping(target = "ultimoAcesso", ignore = true)
    @Mapping(target = "inativo", ignore = true)
    CredencialCodigoBarra toEntity(CredencialCodigoBarraRequestDTO dto);
}