package com.autobots.automanager.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

class GeradorJwt {

    private final SecretKey chave;
    private final Date expiracao;

    GeradorJwt(String assinatura, long duracao) {
        this.chave = Keys.hmacShaKeyFor(assinatura.getBytes(StandardCharsets.UTF_8));
        this.expiracao = new Date(System.currentTimeMillis() + duracao);
    }

    String gerarJwt(String nomeUsuario) {
        return Jwts.builder()
                .subject(nomeUsuario)
                .expiration(expiracao)
                .signWith(chave)
                .compact();
    }
}