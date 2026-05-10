package com.autobots.automanager.mappers;

import com.autobots.automanager.dtos.request.UsuarioRequestDTO;
import com.autobots.automanager.dtos.response.UsuarioResponseDTO;
import com.autobots.automanager.model.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {TelefoneMapper.class, EnderecoMapper.class, DocumentoMapper.class, EmailMapper.class})
public interface UsuarioMapper {
    UsuarioResponseDTO toDTO(Usuario usuario);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "telefones", ignore = true)
    @Mapping(target = "endereco", ignore = true)
    @Mapping(target = "documentos", ignore = true)
    @Mapping(target = "emails", ignore = true)
    @Mapping(target = "credenciais", ignore = true)
    @Mapping(target = "mercadorias", ignore = true)
    @Mapping(target = "vendas", ignore = true)
    @Mapping(target = "veiculos", ignore = true)
    Usuario toEntity(UsuarioRequestDTO usuarioRequestDTO);
}