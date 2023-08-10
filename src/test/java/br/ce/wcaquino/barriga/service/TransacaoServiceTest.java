package br.ce.wcaquino.barriga.service;

import static br.ce.wcaquino.barriga.dominio.builders.TransacaoBuilder.umTransacao;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import br.ce.wcaquino.barriga.dominio.Conta;
import br.ce.wcaquino.barriga.dominio.Transacao;
import br.ce.wcaquino.barriga.dominio.builders.ContaBuilder;
import br.ce.wcaquino.barriga.dominio.exceptions.ValidationException;
import br.ce.wcaquino.barriga.service.external.ClockService;
import br.ce.wcaquino.barriga.service.repositories.TransacaoDao;

//@EnabledIf(value = "isHoraValida")
@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {

	@InjectMocks @Spy
	private TransacaoService service;
	@Mock
	private TransacaoDao dao;
	@Mock private ClockService clockService;

	@BeforeEach
	public void checkTime() {
		// Assumptions.assumeTrue(LocalDateTime.now().getHour() < 16);
	}

	/*
	 * public static boolean isHoraValida() { return LocalDateTime.now().getHour() <
	 * 16; }
	 */
	
	@BeforeEach
	public void setup() {
		//Mockito.when(clockService.getCurrentTime()).thenReturn(LocalDateTime.of(2023, 1,1,14,30,28));
		Mockito.when(service.getTime()).thenReturn(LocalDateTime.of(2023, 1,1,14,30,28));
	}

	@Test
	public void deveSalvarTransacaoValida() {

		Transacao transacaoParaSalvar = umTransacao().comId(null).agora();

		// LocalDateTime dataDesejada = LocalDateTime.of(2023, 1,1,14,30,28);
		
		/*try(MockedConstruction<Date> date 
				= Mockito.mockConstruction(Date.class, (mock,context) -> {
					
					Mockito.when(mock.getHours()).thenReturn(14);
					
				})){*/
		// try(MockedStatic<LocalDateTime> ldt =
		// Mockito.mockStatic(LocalDateTime.class)){
		// ldt.when(() -> LocalDateTime.now()).thenReturn(dataDesejada);

		Mockito.when(dao.salvar(transacaoParaSalvar)).thenReturn(umTransacao().agora());
		


		Transacao transacaoSalva = service.salvar(transacaoParaSalvar);

		assertAll("Transação", () -> assertNotNull(transacaoSalva.getId()),
				() -> assertEquals("Transação Válida", transacaoSalva.getDescricao()), () -> {
					assertAll("Conta", () -> assertEquals("Conta Válida", transacaoSalva.getConta().nome()), () -> {
						assertAll("Usuário",
								() -> assertEquals("Usuário Válido", transacaoSalva.getConta().usuario().nome()),
								() -> assertEquals("12345678", transacaoSalva.getConta().usuario().senha()));
					}

				);
				});

	}

	@ParameterizedTest(name = "{6}")
	@MethodSource(value = "cenariosObrigatorios")
	public void deveValidarCamposObrigatoriosAoSalvar(Long id, String descricao, Double valor, LocalDate data,
			Conta conta, Boolean status, String mensagem) {

		String exMessage = assertThrows(ValidationException.class, () -> {

			Transacao transacaoParaSalvar = umTransacao().comId(id).comDescricao(descricao).comValor(valor)
					.comConta(conta).comData(data).comStatus(status).agora();
			service.salvar(transacaoParaSalvar);

		}).getMessage();

		Assertions.assertEquals(mensagem, exMessage);

	}

	static Stream<Arguments> cenariosObrigatorios() {

		return Stream.of(
				Arguments.of(1L, null, 10.0, LocalDate.now(), ContaBuilder.umaConta().agora(), false,
						"Descrição inexistente"),
				Arguments.of(1L, "Descricao", null, LocalDate.now(), ContaBuilder.umaConta().agora(), false,
						"Valor inexistente"),
				Arguments.of(1L, "Descricao", 10.0, null, ContaBuilder.umaConta().agora(), false, "Data inexistente"),
				Arguments.of(1L, "Descricao", 10.0, LocalDate.now(), null, false, "Conta inexistente"));

	}

}
