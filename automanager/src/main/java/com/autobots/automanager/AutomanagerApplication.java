package com.autobots.automanager;

import com.autobots.automanager.enums.PerfilUsuario;
import com.autobots.automanager.model.entity.Endereco;
import com.autobots.automanager.model.entity.Telefone;
import com.autobots.automanager.model.entity.Usuario;
import com.autobots.automanager.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class AutomanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutomanagerApplication.class, args);
	}

	@Component
	public static class Runner implements ApplicationRunner {

		@Autowired
		private UsuarioRepositorio repositorio;

		@Override
		public void run(ApplicationArguments args) throws Exception {
			if (repositorio.count() > 0) return;

			Usuario usuario = new Usuario();
			usuario.setNome("Pedro Alcântara de Bragança e Bourbon");
			usuario.setNomeSocial("Dom Pedro");
			usuario.getPerfis().add(PerfilUsuario.FUNCIONARIO);

			Telefone telefone = new Telefone();
			telefone.setDdd("21");
			telefone.setNumero("981234576");
			usuario.getTelefones().add(telefone);

			Endereco endereco = new Endereco();
			endereco.setEstado("Rio de Janeiro");
			endereco.setCidade("Rio de Janeiro");
			endereco.setBairro("Copacabana");
			endereco.setRua("Avenida Atlântica");
			endereco.setNumero("1702");
			endereco.setCodigoPostal("22021001");
			endereco.setInformacoesAdicionais("Hotel Copacabana Palace");
			usuario.setEndereco(endereco);

			repositorio.save(usuario);
		}
	}
}
//Vejo uma macumba lá fora todo mundo vai morre.