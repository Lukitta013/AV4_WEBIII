package com.autobots.automanager.mappers;

import com.autobots.automanager.dtos.request.MercadoriaRequestDTO;
import com.autobots.automanager.dtos.response.MercadoriaResponseDTO;
import com.autobots.automanager.model.entity.Mercadoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MercadoriaMapper {
    MercadoriaResponseDTO toDTO(Mercadoria mercadoria);

    @Mapping(target = "id", ignore = true)
    Mercadoria toEntity(MercadoriaRequestDTO mercadoriaRequestDTO);
}