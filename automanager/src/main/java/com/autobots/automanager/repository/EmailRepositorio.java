package com.autobots.automanager.repository;

import com.autobots.automanager.model.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepositorio extends JpaRepository<Email, Long> {
}