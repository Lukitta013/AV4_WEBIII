package com.autobots.automanager.mappers;

import com.autobots.automanager.dtos.request.EmailRequestDTO;
import com.autobots.automanager.dtos.response.EmailResponseDTO;
import com.autobots.automanager.model.entity.Email;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmailMapper {
    EmailResponseDTO toDTO(Email email);

    @Mapping(target = "id", ignore = true)
    Email toEntity(EmailRequestDTO emailRequestDTO);
}