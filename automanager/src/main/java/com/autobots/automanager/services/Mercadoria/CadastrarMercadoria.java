package com.autobots.automanager.services.Mercadoria;

import com.autobots.automanager.dtos.request.MercadoriaRequestDTO;
import com.autobots.automanager.dtos.response.MercadoriaResponseDTO;
import com.autobots.automanager.mappers.MercadoriaMapper;
import com.autobots.automanager.model.entity.Mercadoria;
import com.autobots.automanager.repository.MercadoriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CadastrarMercadoria {
    @Autowired private MercadoriaRepositorio mercadoriaRepositorio;
    @Autowired private MercadoriaMapper mercadoriaMapper;

    public MercadoriaResponseDTO cadastrarMercadoria(MercadoriaRequestDTO dto) {
        Mercadoria mercadoria = mercadoriaMapper.toEntity(dto);
        mercadoria.setCadastro(new Date());
        return mercadoriaMapper.toDTO(mercadoriaRepositorio.save(mercadoria));
    }
}