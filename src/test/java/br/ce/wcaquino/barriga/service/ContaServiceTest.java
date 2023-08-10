package br.ce.wcaquino.barriga.service;

import static br.ce.wcaquino.barriga.dominio.builders.ContaBuilder.umaConta;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.security.Provider.Service;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.ce.wcaquino.barriga.dominio.Conta;
import br.ce.wcaquino.barriga.dominio.builders.ContaBuilder;
import br.ce.wcaquino.barriga.dominio.exceptions.ValidationException;
import br.ce.wcaquino.barriga.service.external.ContaEvent;
import br.ce.wcaquino.barriga.service.external.ContaEvent.EventType;
import br.ce.wcaquino.barriga.service.repositories.ContaRepository;

@ExtendWith(MockitoExtension.class)
public class ContaServiceTest {
	
	@Mock
	private ContaRepository contaRepository;
	
	@InjectMocks
	private ContaService contaService;
	
	@Mock private ContaEvent event;

	@Test
	public void deveSalvarComSucesso() {
		
		Conta contaToSave = umaConta().comId(null).agora();
		
		when(contaRepository.salvar(contaToSave)).thenReturn(umaConta().agora());
		
		doNothing().when(event).dispatch(umaConta().agora(), EventType.CREATED);
		
		Conta conta = contaService.salvar(contaToSave);
		
		assertNotNull(conta.id());
		
	}
	
	@Test
	public void deveRejeitarContaRepetida() {
		
		Conta contaToSave = umaConta().comId(null).agora();
		
		when(contaRepository.obterContasPorUsuario(contaToSave.usuario().id()))
		.thenReturn(Arrays.asList(umaConta().agora()));
		
		String mensagem = Assertions.assertThrows(ValidationException.class, () -> contaService.salvar(contaToSave)).getMessage();
		
		assertEquals("Usuário já possui uma conta com este nome", mensagem);
		
	}
	
	@Test
	public void deveSalvarContaMEsmoJaExistindoOutras() {
		
		Conta contaToSave = umaConta().comId(null).agora();
		
		when(contaRepository.obterContasPorUsuario(contaToSave.usuario().id()))
		.thenReturn(Arrays.asList(umaConta().comNome("Outra conta").agora()));
		
		when(contaRepository.salvar(contaToSave)).thenReturn(umaConta().agora());
		
		Conta conta = contaService.salvar(contaToSave);
		
		assertNotNull(conta.id());
		
	}
	
}
