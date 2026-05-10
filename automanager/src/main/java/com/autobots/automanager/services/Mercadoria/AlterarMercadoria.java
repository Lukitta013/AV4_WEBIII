package com.autobots.automanager.services.Mercadoria;

import com.autobots.automanager.dtos.request.MercadoriaRequestDTO;
import com.autobots.automanager.dtos.response.MercadoriaResponseDTO;
import com.autobots.automanager.exceptions.RecursoNaoEncontradoException;
import com.autobots.automanager.mappers.MercadoriaMapper;
import com.autobots.automanager.model.entity.Mercadoria;
import com.autobots.automanager.repository.MercadoriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlterarMercadoria {
    @Autowired private MercadoriaRepositorio mercadoriaRepositorio;
    @Autowired private MercadoriaMapper mercadoriaMapper;

    public MercadoriaResponseDTO alterarMercadoria(Long id, MercadoriaRequestDTO dto) {
        Mercadoria mercadoria = mercadoriaRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Mercadoria", id));
        mercadoria.setNome(dto.getNome());
        mercadoria.setValor(dto.getValor());
        mercadoria.setQuantidade(dto.getQuantidade());
        mercadoria.setDescricao(dto.getDescricao());
        mercadoria.setValidade(dto.getValidade());
        mercadoria.setFabricacao(dto.getFabricacao());
        return mercadoriaMapper.toDTO(mercadoriaRepositorio.save(mercadoria));
    }
}