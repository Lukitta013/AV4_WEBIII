package com.autobots.automanager.mappers;

import com.autobots.automanager.dtos.request.EmpresaRequestDTO;
import com.autobots.automanager.dtos.response.EmpresaResponseDTO;
import com.autobots.automanager.model.entity.Empresa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {TelefoneMapper.class, EnderecoMapper.class})
public interface EmpresaMapper {

    @Mapping(target = "usuarioIds", ignore = true)
    @Mapping(target = "servicoIds", ignore = true)
    @Mapping(target = "vendaIds", ignore = true)
    EmpresaResponseDTO toDTO(Empresa empresa);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cadastro", ignore = true)
    @Mapping(target = "telefones", ignore = true)
    @Mapping(target = "endereco", ignore = true)
    @Mapping(target = "usuarios", ignore = true)
    @Mapping(target = "servicos", ignore = true)
    @Mapping(target = "vendas", ignore = true)
    Empresa toEntity(EmpresaRequestDTO empresaRequestDTO);
}