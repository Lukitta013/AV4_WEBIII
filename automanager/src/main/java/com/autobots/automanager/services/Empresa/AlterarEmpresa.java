package com.autobots.automanager.services.Empresa;

import com.autobots.automanager.dtos.request.EmpresaRequestDTO;
import com.autobots.automanager.dtos.response.EmpresaResponseDTO;
import com.autobots.automanager.exceptions.RecursoNaoEncontradoException;
import com.autobots.automanager.mappers.EmpresaMapper;
import com.autobots.automanager.model.entity.Empresa;
import com.autobots.automanager.repository.EmpresaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlterarEmpresa {
    @Autowired private EmpresaRepositorio empresaRepositorio;
    @Autowired private EmpresaMapper empresaMapper;

    public EmpresaResponseDTO alterarEmpresa(Long id, EmpresaRequestDTO dto) {
        Empresa empresa = empresaRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Empresa", id));
        empresa.setRazaoSocial(dto.getRazaoSocial());
        empresa.setNomeFantasia(dto.getNomeFantasia());
        return empresaMapper.toDTO(empresaRepositorio.save(empresa));
    }
}