package com.autobots.automanager.filtros;

import com.autobots.automanager.dtos.request.LoginRequestDTO;
import com.autobots.automanager.jwt.ProvedorJwt;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;

public class Autenticador extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager gerenciadorAutenticacao;
    private final ProvedorJwt provedorJwt;

    public Autenticador(AuthenticationManager gerenciadorAutenticacao, ProvedorJwt provedorJwt) {
        this.gerenciadorAutenticacao = gerenciadorAutenticacao;
        this.provedorJwt = provedorJwt;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
            throws AuthenticationException {
        LoginRequestDTO credencial;
        try {
            credencial = new ObjectMapper()
                    .readValue(request.getInputStream(), LoginRequestDTO.class);
        } catch (IOException e) {
            credencial = new LoginRequestDTO();
            credencial.setNomeUsuario("");
            credencial.setSenha("");
        }

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(
                        credencial.getNomeUsuario(),
                        credencial.getSenha(),
                        new ArrayList<>()
                );

        return gerenciadorAutenticacao.authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication autenticacao)
            throws IOException {
        UserDetails usuario = (UserDetails) autenticacao.getPrincipal();
        String jwt = provedorJwt.proverJwt(usuario.getUsername());
        response.addHeader("Authorization", "Bearer " + jwt);
    }
}