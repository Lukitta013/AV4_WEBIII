package com.autobots.automanager.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Mercadoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Date validade;
    @Column(nullable = false)
    private Date fabricacao;
    @Column(nullable = false)
    private Date cadastro;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private long quantidade;
    @Column(nullable = false)
    private double valor;
    @Column
    private String descricao;
}