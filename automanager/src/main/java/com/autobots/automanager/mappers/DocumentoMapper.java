package com.autobots.automanager.mappers;

import com.autobots.automanager.dtos.request.DocumentoRequestDTO;
import com.autobots.automanager.dtos.response.DocumentoResponseDTO;
import com.autobots.automanager.model.entity.Documento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DocumentoMapper {
    @Mapping(target = "tipo", source = "tipoDocumento")
    DocumentoResponseDTO toDTO(Documento documento);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tipoDocumento", source = "tipo")
    Documento toEntity(DocumentoRequestDTO documentoRequestDTO);
}