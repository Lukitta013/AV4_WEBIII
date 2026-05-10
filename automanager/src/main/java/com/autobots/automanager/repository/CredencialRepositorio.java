package com.autobots.automanager.repository;

import com.autobots.automanager.model.entity.Credencial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredencialRepositorio extends JpaRepository<Credencial, Long> {
}