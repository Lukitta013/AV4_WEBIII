package com.autobots.automanager.services.Venda;

import com.autobots.automanager.dtos.request.VendaRequestDTO;
import com.autobots.automanager.dtos.response.VendaResponseDTO;
import com.autobots.automanager.exceptions.RecursoNaoEncontradoException;
import com.autobots.automanager.mappers.VendaMapper;
import com.autobots.automanager.model.entity.*;
import com.autobots.automanager.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class AlterarVenda {
    @Autowired private VendaRepositorio vendaRepositorio;
    @Autowired private UsuarioRepositorio usuarioRepositorio;
    @Autowired private VeiculoRepositorio veiculoRepositorio;
    @Autowired private MercadoriaRepositorio mercadoriaRepositorio;
    @Autowired private ServicoRepositorio servicoRepositorio;
    @Autowired private VendaMapper vendaMapper;

    public VendaResponseDTO alterarVenda(Long id, VendaRequestDTO dto) {
        Venda venda = vendaRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Venda", id));
        venda.setIdentificacao(dto.getIdentificacao());
        if (dto.getClienteId() != null)
            venda.setCliente(usuarioRepositorio.findById(dto.getClienteId())
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Usuario", dto.getClienteId())));
        if (dto.getFuncionarioId() != null)
            venda.setFuncionario(usuarioRepositorio.findById(dto.getFuncionarioId())
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Usuario", dto.getFuncionarioId())));
        if (dto.getVeiculoId() != null)
            venda.setVeiculo(veiculoRepositorio.findById(dto.getVeiculoId())
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Veiculo", dto.getVeiculoId())));
        if (dto.getMercadoriaIds() != null)
            venda.setMercadorias(new HashSet<>(mercadoriaRepositorio.findAllById(dto.getMercadoriaIds())));
        if (dto.getServicoIds() != null)
            venda.setServicos(new HashSet<>(servicoRepositorio.findAllById(dto.getServicoIds())));
        return vendaMapper.toDTO(vendaRepositorio.save(venda));
    }
}

