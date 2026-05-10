package com.autobots.automanager.services.Empresa;

import com.autobots.automanager.dtos.request.EmpresaRequestDTO;
import com.autobots.automanager.dtos.response.EmpresaResponseDTO;
import com.autobots.automanager.mappers.EmpresaMapper;
import com.autobots.automanager.model.entity.Empresa;
import com.autobots.automanager.repository.EmpresaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CadastrarEmpresa {
    @Autowired private EmpresaRepositorio empresaRepositorio;
    @Autowired private EmpresaMapper empresaMapper;

    public EmpresaResponseDTO cadastrarEmpresa(EmpresaRequestDTO dto) {
        Empresa empresa = empresaMapper.toEntity(dto);
        empresa.setCadastro(new Date());
        return empresaMapper.toDTO(empresaRepositorio.save(empresa));
    }
}