package com.autobots.automanager.mappers;

import com.autobots.automanager.dtos.request.EnderecoRequestDTO;
import com.autobots.automanager.dtos.response.EnderecoResponseDTO;
import com.autobots.automanager.model.entity.Endereco;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {
    EnderecoResponseDTO toDTO(Endereco endereco);

    @Mapping(target = "id", ignore = true)
    Endereco toEntity(EnderecoRequestDTO enderecoRequestDTO);
}