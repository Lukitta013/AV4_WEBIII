package com.autobots.automanager.exceptions;

public class RecursoNaoEncontradoException extends RuntimeException {
    public RecursoNaoEncontradoException(String entidade, Long id) {
        super(entidade + " com id " + id + " nao encontrado");
    }
}