package com.autobots.automanager.jwt;

import io.jsonwebtoken.Claims;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class ProvedorJwt {

    @Value("${jwt.secret}")
    private String assinatura;

    @Value("${jwt.expiration}")
    private Long duracao;

    public String proverJwt(String nomeUsuario) {
        return new GeradorJwt(assinatura, duracao).gerarJwt(nomeUsuario);
    }

    public boolean validarJwt(String jwt) {
        AnalisadorJwt analisador = new AnalisadorJwt(assinatura, jwt);
        return new ValidadorJwt().validar(analisador.obterReivindicacoes());
    }

    public String obterNomeUsuario(String jwt) {
        AnalisadorJwt analisador = new AnalisadorJwt(assinatura, jwt);
        Claims claims = analisador.obterReivindicacoes();
        return analisador.obterNomeUsuario(claims);
    }
}