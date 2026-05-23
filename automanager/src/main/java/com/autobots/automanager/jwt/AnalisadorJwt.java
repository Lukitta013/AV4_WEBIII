package com.autobots.automanager.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

class AnalisadorJwt {

    private final SecretKey chave;
    private final String jwt;

    AnalisadorJwt(String assinatura, String jwt) {
        this.chave = Keys.hmacShaKeyFor(assinatura.getBytes(StandardCharsets.UTF_8));
        this.jwt = jwt;
    }

    Claims obterReivindicacoes() {
        try {
            return Jwts.parser()
                    .verifyWith(chave)
                    .build()
                    .parseSignedClaims(jwt)
                    .getPayload();
        } catch (Exception e) {
            return null;
        }
    }

    String obterNomeUsuario(Claims reivindicacoes) {
        return reivindicacoes != null ? reivindicacoes.getSubject() : null;
    }
}