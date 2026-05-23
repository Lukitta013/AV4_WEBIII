package com.autobots.automanager.jwt;

import io.jsonwebtoken.Claims;

import java.util.Date;

class ValidadorJwt {

    boolean validar(Claims reivindicacoes) {
        if (reivindicacoes == null) return false;

        String nomeUsuario = reivindicacoes.getSubject();
        Date expiracao     = reivindicacoes.getExpiration();
        Date agora         = new Date(System.currentTimeMillis());

        return nomeUsuario != null
                && !nomeUsuario.isBlank()
                && expiracao != null
                && agora.before(expiracao);
    }
}