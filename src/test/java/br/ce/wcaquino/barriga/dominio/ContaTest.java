package br.ce.wcaquino.barriga.dominio;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import br.ce.wcaquino.barriga.dominio.builders.ContaBuilder;
import br.ce.wcaquino.barriga.dominio.builders.UsuarioBuilder;
import br.ce.wcaquino.barriga.dominio.exceptions.ValidationException;

public class ContaTest {

	@Test
	public void deveCriarUmaContaValida() {

		// Criar uma conta
		Conta conta = ContaBuilder.umaConta().agora();

		Assertions.assertAll("Conta", () -> Assertions.assertEquals(1L, conta.id()),
				() -> Assertions.assertEquals("Conta Válida", conta.nome()),
				() -> Assertions.assertEquals(UsuarioBuilder.umUsuario().agora(), conta.usuario()));
	}

	@ParameterizedTest(name = "[{index}] - {3}")
	@MethodSource(value = "dataProvider")
	public void deveRejeitarUmaContaInvalida(Long id, String nome, Usuario usuario, String mensagem) {
		String erroMessage = Assertions.assertThrows(ValidationException.class,
				() -> ContaBuilder.umaConta().comId(id).comNome(nome).comUsuario(usuario).agora()).getMessage();
		
		Assertions.assertEquals(mensagem, erroMessage);

	}
	
	private static Stream<Arguments> dataProvider(){
		return Stream.of(
				Arguments.of(1L, null, UsuarioBuilder.umUsuario().agora(),"Nome é obrigatório"),
				Arguments.of(1L, "Conta Válida", null ,"Usuário é obrigatório")
		);
	}

}
