package br.ce.wcaquino.barriga.infra;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import br.ce.wcaquino.barriga.dominio.Usuario;
import br.ce.wcaquino.barriga.dominio.builders.UsuarioBuilder;
import br.ce.wcaquino.barriga.dominio.exceptions.ValidationException;
import br.ce.wcaquino.barriga.service.UsuarioService;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuarioServiceComUsuarioMemoryRepositoryTest {
	
	private UsuarioService service = new UsuarioService(new UsuarioMemoryRepository());
	
	
	@Test
	@Order(1)
	public void deveSalvarUsuarioValido() {
		Usuario usuario = service.salvar(UsuarioBuilder.umUsuario().comEmail("a@a.com").comId(null).agora());
		Assertions.assertNotNull(usuario.id());
	//	Assertions.assertEquals(2L, usuario.id());
	}
	
	/*
	 * @Test public void deveSalvarUsuarioValido2() { Usuario usuario =
	 * service.salvar(UsuarioBuilder.umUsuario().comId(null).comEmail("a@a.com").
	 * agora()); Assertions.assertNotNull(usuario.id()); Assertions.assertEquals(3L,
	 * usuario.id()); }
	 */
	
	@Test
	@Order(2)
	public void deveRejeitarUsuarioExistente() {
		ValidationException ve =  Assertions.assertThrows(ValidationException.class, () -> service.salvar(UsuarioBuilder.umUsuario().comId(null).agora()));
		
		Assertions.assertEquals("Usuário user@mail.com já cadastrado",ve.getMessage());
		
		
	}

}
