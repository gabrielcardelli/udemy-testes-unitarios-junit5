package br.ce.wcaquino.barriga.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.ce.wcaquino.barriga.dominio.Usuario;
import br.ce.wcaquino.barriga.dominio.builders.UsuarioBuilder;
import br.ce.wcaquino.barriga.infra.UsuarioDummyRepository;

public class UsuarioServiceTest {
	
	private UsuarioService usuarioService;
	
	public UsuarioServiceTest() {
		usuarioService = new UsuarioService(new UsuarioDummyRepository());
	}
	
	@Test
	public void deveSalvarUsuarioValido() {
		
		Usuario usuario = UsuarioBuilder.umUsuario().comId(null).comEmail("outro@email.com").agora();
		Usuario usuarioSalvo = usuarioService.salvar(usuario);
		
		Assertions.assertNotNull(usuarioSalvo.id());
		
	}

}
