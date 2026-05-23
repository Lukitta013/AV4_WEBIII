package com.autobots.automanager.services.Usuario;

import com.autobots.automanager.dtos.request.UsuarioRequestDTO;
import com.autobots.automanager.dtos.response.UsuarioResponseDTO;
import com.autobots.automanager.enums.PerfilUsuario;
import com.autobots.automanager.exceptions.RecursoNaoEncontradoException;
import com.autobots.automanager.mappers.UsuarioMapper;
import com.autobots.automanager.model.entity.Usuario;
import com.autobots.automanager.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@Service
public class AlterarUsuario {

    @Autowired private UsuarioRepositorio usuarioRepositorio;
    @Autowired private UsuarioMapper usuarioMapper;

    public UsuarioResponseDTO alterarUsuario(Long id, UsuarioRequestDTO dto) {
        Usuario usuario = usuarioRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuario", id));

        // Valida se quem está logado pode alterar o perfil deste usuário
        validarPerfisPermitidos(dto.getPerfis(), usuario.getPerfis());

        usuario.setNome(dto.getNome());
        usuario.setNomeSocial(dto.getNomeSocial());
        if (dto.getPerfis() != null) usuario.setPerfis(dto.getPerfis());

        return usuarioMapper.toDTO(usuarioRepositorio.save(usuario));
    }
    private void validarPerfisPermitidos(Set<PerfilUsuario> perfisNovos,
                                         Set<PerfilUsuario> perfisAtuais) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        boolean isAdmin    = auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMINISTRADOR"));
        boolean isGerente  = auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_GERENTE"));
        boolean isVendedor = auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_VENDEDOR"));

        if (isAdmin) return;
        if (perfisAtuais != null && perfisAtuais.contains(PerfilUsuario.ROLE_ADMINISTRADOR)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "Somente o Administrador pode alterar outro Administrador.");
        }

        if (perfisNovos == null || perfisNovos.isEmpty()) return;

        for (PerfilUsuario perfil : perfisNovos) {
            if (isGerente) {
                if (perfil == PerfilUsuario.ROLE_ADMINISTRADOR) {
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                            "Gerente não tem permissão para atribuir o perfil ADMINISTRADOR.");
                }
            } else if (isVendedor) {
                if (perfil != PerfilUsuario.ROLE_CLIENTE) {
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                            "Vendedor só tem permissão para alterar usuários com perfil CLIENTE.");
                }
            }
        }
    }
}