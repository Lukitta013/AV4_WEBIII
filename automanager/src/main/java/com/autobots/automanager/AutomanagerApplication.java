package com.autobots.automanager;

import com.autobots.automanager.enums.PerfilUsuario;
import com.autobots.automanager.model.entity.CredencialUsuarioSenha;
import com.autobots.automanager.model.entity.Endereco;
import com.autobots.automanager.model.entity.Telefone;
import com.autobots.automanager.model.entity.Usuario;
import com.autobots.automanager.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@SpringBootApplication
public class AutomanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutomanagerApplication.class, args);
	}

	@Component
	public static class Runner implements ApplicationRunner {

		@Autowired
		private UsuarioRepositorio repositorio;

		private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		@Override
		public void run(ApplicationArguments args) {
			if (repositorio.count() > 0) return;

			repositorio.save(criarUsuario(
					"Administrador do Sistema", "Admin",
					PerfilUsuario.ROLE_ADMINISTRADOR,
					"admin", "admin123",
					"11", "999999999", null));

			repositorio.save(criarUsuario(
					"Pedro Alcântara de Bragança e Bourbon", "Dom Pedro",
					PerfilUsuario.ROLE_GERENTE,
					"gerente", "gerente123",
					"21", "981234576",
					criarEndereco("Rio de Janeiro", "Rio de Janeiro",
							"Copacabana", "Avenida Atlântica", "1702",
							"22021001", "Hotel Copacabana Palace")));

			repositorio.save(criarUsuario(
					"Vendedor Exemplo", "Vendedor",
					PerfilUsuario.ROLE_VENDEDOR,
					"vendedor", "vendedor123",
					"11", "988887777", null));

			repositorio.save(criarUsuario(
					"Cliente Exemplo", "Cliente",
					PerfilUsuario.ROLE_CLIENTE,
					"cliente", "cliente123",
					"11", "977776666", null));
		}

		private Usuario criarUsuario(String nome, String nomeSocial,
		                             PerfilUsuario perfil,
		                             String login, String senhaPlana,
		                             String ddd, String numero,
		                             Endereco endereco) {
			Usuario u = new Usuario();
			u.setNome(nome);
			u.setNomeSocial(nomeSocial);
			u.getPerfis().add(perfil);

			CredencialUsuarioSenha cred = new CredencialUsuarioSenha();
			cred.setNomeUsuario(login);
			cred.setSenha(encoder.encode(senhaPlana));
			cred.setCriacao(new Date());
			cred.setInativo(false);
			u.getCredenciais().add(cred);

			Telefone tel = new Telefone();
			tel.setDdd(ddd);
			tel.setNumero(numero);
			u.getTelefones().add(tel);

			if (endereco != null) u.setEndereco(endereco);
			return u;
		}

		private Endereco criarEndereco(String estado, String cidade, String bairro,
		                               String rua, String numero, String cep,
		                               String info) {
			Endereco e = new Endereco();
			e.setEstado(estado);
			e.setCidade(cidade);
			e.setBairro(bairro);
			e.setRua(rua);
			e.setNumero(numero);
			e.setCodigoPostal(cep);
			e.setInformacoesAdicionais(info);
			return e;
		}
	}
}