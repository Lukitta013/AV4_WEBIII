package com.autobots.automanager.model.entity;

import com.autobots.automanager.enums.TipoDocumento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Documento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private TipoDocumento tipoDocumento;
	@Column(unique = true)
	@NotBlank(message = "Número é obrigatório")
	private String numero;
}