package com.autobots.automanager.security;

import com.autobots.automanager.model.entity.CredencialUsuarioSenha;
import com.autobots.automanager.model.entity.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    private final Usuario usuario;
    private final CredencialUsuarioSenha credencial;

    public UserDetailsImpl(Usuario usuario) {
        this.usuario = usuario;
        this.credencial = usuario.getCredenciais()
                .stream()
                .filter(c -> c instanceof CredencialUsuarioSenha)
                .map(c -> (CredencialUsuarioSenha) c)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return usuario.getPerfis()
                .stream()
                .map(p -> new SimpleGrantedAuthority(p.name()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return credencial != null ? credencial.getSenha() : "";
    }

    @Override
    public String getUsername() {
        return credencial != null ? credencial.getNomeUsuario() : "";
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() {
        return credencial == null || !credencial.isInativo();
    }
}