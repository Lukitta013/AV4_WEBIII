package com.autobots.automanager.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErroResponse {
    private int status;
    private String mensagem;
    private LocalDateTime dataHora;
}