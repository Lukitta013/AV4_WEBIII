package com.autobots.automanager.repository;

import com.autobots.automanager.model.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepositorio extends JpaRepository<Servico, Long> {
}