package com.autobots.automanager.repository;

import com.autobots.automanager.model.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepositorio extends JpaRepository<Veiculo, Long> {
}