package com.autobots.automanager.services.Usuario;

import com.autobots.automanager.dtos.request.UsuarioRequestDTO;
import com.autobots.automanager.dtos.response.UsuarioResponseDTO;
import com.autobots.automanager.enums.PerfilUsuario;
import com.autobots.automanager.exceptions.RecursoNaoEncontradoException;
import com.autobots.automanager.mappers.UsuarioMapper;
import com.autobots.automanager.model.entity.CredencialUsuarioSenha;
import com.autobots.automanager.model.entity.Usuario;
import com.autobots.automanager.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.Set;

@Service
public class CadastrarUsuario {

    @Autowired private UsuarioRepositorio usuarioRepositorio;
    @Autowired private UsuarioMapper usuarioMapper;
    @Autowired private PasswordEncoder passwordEncoder;

    public UsuarioResponseDTO cadastrarUsuario(UsuarioRequestDTO dto) {
        validarPerfisPermitidos(dto.getPerfis());

        Usuario usuario = usuarioMapper.toEntity(dto);

        boolean temCredencial = dto.getNomeUsuario() != null
                && !dto.getNomeUsuario().isBlank()
                && dto.getSenha() != null
                && !dto.getSenha().isBlank();

        if (temCredencial) {
            CredencialUsuarioSenha credencial = new CredencialUsuarioSenha();
            credencial.setNomeUsuario(dto.getNomeUsuario());
            credencial.setSenha(passwordEncoder.encode(dto.getSenha()));
            credencial.setCriacao(new Date());
            credencial.setInativo(false);
            usuario.getCredenciais().add(credencial);
        }

        return usuarioMapper.toDTO(usuarioRepositorio.save(usuario));
    }

    private void validarPerfisPermitidos(Set<PerfilUsuario> perfisDesejados) {
        if (perfisDesejados == null || perfisDesejados.isEmpty()) return;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        boolean isAdmin    = auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMINISTRADOR"));
        boolean isGerente  = auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_GERENTE"));
        boolean isVendedor = auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_VENDEDOR"));

        for (PerfilUsuario perfil : perfisDesejados) {
            if (isAdmin) {
                continue;
            }
            if (isGerente) {
                if (perfil == PerfilUsuario.ROLE_ADMINISTRADOR) {
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                            "Gerente não tem permissão para atribuir o perfil ADMINISTRADOR.");
                }
                continue;
            }
            if (isVendedor) {
                if (perfil != PerfilUsuario.ROLE_CLIENTE) {
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                            "Vendedor só tem permissão para criar usuários com perfil CLIENTE.");
                }
            }
        }
    }
}