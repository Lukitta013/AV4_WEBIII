package com.autobots.automanager.mappers;

import com.autobots.automanager.dtos.request.VendaRequestDTO;
import com.autobots.automanager.dtos.response.VendaResponseDTO;
import com.autobots.automanager.model.entity.Venda;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {MercadoriaMapper.class, ServicoMapper.class})
public interface VendaMapper {
    @Mapping(target = "clienteId", source = "cliente.id")
    @Mapping(target = "funcionarioId", source = "funcionario.id")
    @Mapping(target = "veiculoId", source = "veiculo.id")
    VendaResponseDTO toDTO(Venda venda);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    @Mapping(target = "funcionario", ignore = true)
    @Mapping(target = "veiculo", ignore = true)
    @Mapping(target = "mercadorias", ignore = true)
    @Mapping(target = "servicos", ignore = true)
    Venda toEntity(VendaRequestDTO vendaRequestDTO);
}