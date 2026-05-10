package com.autobots.automanager.mappers;

import com.autobots.automanager.dtos.request.CredencialRequestDTO;
import com.autobots.automanager.dtos.response.CredencialResponseDTO;
import com.autobots.automanager.model.entity.Credencial;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CredencialMapper {
    CredencialResponseDTO toDTO(Credencial credencial);

    @Mapping(target = "id", ignore = true)
    Credencial toEntity(CredencialRequestDTO credencialRequestDTO);
}