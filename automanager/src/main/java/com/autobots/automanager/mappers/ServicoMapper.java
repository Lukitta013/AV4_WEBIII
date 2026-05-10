package com.autobots.automanager.mappers;

import com.autobots.automanager.dtos.request.ServicoRequestDTO;
import com.autobots.automanager.dtos.response.ServicoResponseDTO;
import com.autobots.automanager.model.entity.Servico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ServicoMapper {
    ServicoResponseDTO toDTO(Servico servico);

    @Mapping(target = "id", ignore = true)
    Servico toEntity(ServicoRequestDTO servicoRequestDTO);
}