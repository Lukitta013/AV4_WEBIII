package com.autobots.automanager.repository;

import com.autobots.automanager.model.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepositorio extends JpaRepository<Venda, Long> {
}