package br.ce.wcaquino.barriga.service;

import static org.mockito.Mockito.times;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.ce.wcaquino.barriga.dominio.Usuario;
import br.ce.wcaquino.barriga.dominio.builders.UsuarioBuilder;
import br.ce.wcaquino.barriga.dominio.exceptions.ValidationException;
import br.ce.wcaquino.barriga.service.repositories.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

	@Mock
	private UsuarioRepository repository;

	@InjectMocks
	private UsuarioService usuarioService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@AfterEach
	public void tearDown() {

	}

	/*
	 * public UsuarioServiceTest() { usuarioService = new UsuarioService(new
	 * UsuarioDummyRepository()); }
	 * 
	 * @Test public void deveSalvarUsuarioValido() {
	 * 
	 * Usuario usuario =
	 * UsuarioBuilder.umUsuario().comId(null).comEmail("outro@email.com").agora();
	 * Usuario usuarioSalvo = usuarioService.salvar(usuario);
	 * 
	 * Assertions.assertNotNull(usuarioSalvo.id());
	 * 
	 * }
	 */

	@Test
	public void deveRetornarEmptyQuandoUsuarioInexistente() {

		Optional<Usuario> usuario = usuarioService.getUsuarioPorEmail("mail@mail.com");

		Assertions.assertTrue(usuario.isEmpty());

	}

	@Test
	public void deveRetornarUsuarioPorEmail() {

		Mockito.when(repository.getUsuarioPorEmail("mail@mail.com"))
				.thenReturn(Optional.of(UsuarioBuilder.umUsuario().agora()));

		Optional<Usuario> usuario = usuarioService.getUsuarioPorEmail("mail@mail.com");

		Assertions.assertTrue(usuario.isPresent());

		/*
		 * Mockito.verify(repository,
		 * Mockito.atLeastOnce()).getUsuarioPorEmail("mail@mail.com");
		 * Mockito.verify(repository,
		 * Mockito.never()).getUsuarioPorEmail("outroEmail@email.com");
		 * Mockito.verifyNoMoreInteractions(repository);
		 */

	}

	@Test
	public void devSalvarUsuarioComSucesso() {

		Usuario usuarioSalvar = UsuarioBuilder.umUsuario().comId(null).agora();

		Mockito.when(repository.salvar(usuarioSalvar)).thenReturn(UsuarioBuilder.umUsuario().agora());

		Usuario usuarioSalvo = usuarioService.salvar(usuarioSalvar);

		Assertions.assertNotNull(usuarioSalvo.id());

		Mockito.verify(repository).getUsuarioPorEmail(usuarioSalvar.email());

		Mockito.verify(repository).salvar(usuarioSalvar);

	}

	@Test
	public void deveRejeitarUsuarioExistente() {
		
		Usuario usuarioParaSalvar = UsuarioBuilder.umUsuario().comId(null).agora();
		
		Mockito.when(repository.getUsuarioPorEmail(usuarioParaSalvar.email())).thenReturn(Optional.of(UsuarioBuilder.umUsuario().agora()));
		
		ValidationException ve = Assertions.assertThrows(ValidationException.class,
				() -> usuarioService.salvar(usuarioParaSalvar));

		Assertions.assertTrue(ve.getMessage().endsWith("já cadastrado"));
		
		Mockito.verify(repository,Mockito.never()).salvar(usuarioParaSalvar);
		
	}

}
