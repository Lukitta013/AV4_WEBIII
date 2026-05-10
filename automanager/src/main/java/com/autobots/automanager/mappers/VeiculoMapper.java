package com.autobots.automanager.mappers;

import com.autobots.automanager.dtos.request.VeiculoRequestDTO;
import com.autobots.automanager.dtos.response.VeiculoResponseDTO;
import com.autobots.automanager.model.entity.Veiculo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VeiculoMapper {
    @Mapping(target = "proprietarioId", source = "proprietario.id")
    VeiculoResponseDTO toDTO(Veiculo veiculo);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "proprietario", ignore = true)
    @Mapping(target = "vendas", ignore = true)
    Veiculo toEntity(VeiculoRequestDTO veiculoRequestDTO);
}