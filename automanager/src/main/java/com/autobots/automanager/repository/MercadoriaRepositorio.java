package com.autobots.automanager.repository;

import com.autobots.automanager.model.entity.Mercadoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MercadoriaRepositorio extends JpaRepository<Mercadoria, Long> {
}