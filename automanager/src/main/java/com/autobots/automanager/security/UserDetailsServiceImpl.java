package com.autobots.automanager.security;

import com.autobots.automanager.model.entity.CredencialUsuarioSenha;
import com.autobots.automanager.model.entity.Usuario;
import com.autobots.automanager.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio repositorio;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario encontrado = repositorio.findAll()
                .stream()
                .filter(u -> u.getCredenciais()
                        .stream()
                        .filter(c -> c instanceof CredencialUsuarioSenha)
                        .map(c -> (CredencialUsuarioSenha) c)
                        .anyMatch(c -> username.equals(c.getNomeUsuario())))
                .findFirst()
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuário não encontrado: " + username));

        return new UserDetailsImpl(encontrado);
    }
}