package com.autobots.automanager.services.Veiculo;

import com.autobots.automanager.dtos.request.VeiculoRequestDTO;
import com.autobots.automanager.dtos.response.VeiculoResponseDTO;
import com.autobots.automanager.exceptions.RecursoNaoEncontradoException;
import com.autobots.automanager.mappers.VeiculoMapper;
import com.autobots.automanager.model.entity.Usuario;
import com.autobots.automanager.model.entity.Veiculo;
import com.autobots.automanager.repository.UsuarioRepositorio;
import com.autobots.automanager.repository.VeiculoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastrarVeiculo {
    @Autowired private VeiculoRepositorio veiculoRepositorio;
    @Autowired private UsuarioRepositorio usuarioRepositorio;
    @Autowired private VeiculoMapper veiculoMapper;

    public VeiculoResponseDTO cadastrarVeiculo(VeiculoRequestDTO dto) {
        Veiculo veiculo = veiculoMapper.toEntity(dto);
        if (dto.getProprietarioId() != null) {
            Usuario proprietario = usuarioRepositorio.findById(dto.getProprietarioId())
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Usuario", dto.getProprietarioId()));
            veiculo.setProprietario(proprietario);
        }
        return veiculoMapper.toDTO(veiculoRepositorio.save(veiculo));
    }
}