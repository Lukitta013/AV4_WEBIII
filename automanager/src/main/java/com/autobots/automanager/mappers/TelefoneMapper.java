package com.autobots.automanager.mappers;

import com.autobots.automanager.dtos.request.TelefoneRequestDTO;
import com.autobots.automanager.dtos.response.TelefoneResponseDTO;
import com.autobots.automanager.model.entity.Telefone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TelefoneMapper {
    TelefoneResponseDTO toDTO(Telefone telefone);

    @Mapping(target = "id", ignore = true)
    Telefone toEntity(TelefoneRequestDTO telefoneRequestDTO);
}