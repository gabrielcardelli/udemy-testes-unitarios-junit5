package br.ce.wcaquino.barriga.service;

import java.time.LocalDateTime;
import java.util.List;

import br.ce.wcaquino.barriga.dominio.Conta;
import br.ce.wcaquino.barriga.dominio.exceptions.ValidationException;
import br.ce.wcaquino.barriga.service.external.ContaEvent;
import br.ce.wcaquino.barriga.service.external.ContaEvent.EventType;
import br.ce.wcaquino.barriga.service.repositories.ContaRepository;

public class ContaService {
	
	private ContaRepository repository;
	
	private ContaEvent event;

	public ContaService(ContaRepository repository, ContaEvent contaEvent) {
		super();
		this.repository = repository;
		this.event = contaEvent;
	}
	
	
	public Conta salvar(Conta conta) {
		
		List<Conta> contas = repository.obterContasPorUsuario(conta.usuario().id());
		
		contas.stream().forEach(c -> {
			if(conta.nome().equals(c.nome())) {
				throw new ValidationException("Usuário já possui uma conta com este nome");
			}
		});
		
		Conta contaSalva = repository.salvar(new Conta(conta.id(), conta.nome() + LocalDateTime.now(), conta.usuario()));
		
		try {
			event.dispatch(contaSalva, EventType.CREATED);
		} catch (Exception e) {
			repository.delete(contaSalva);
			throw new RuntimeException("Falha na criação da conta, tente novamente");
		}
		
		return contaSalva;
	}
	

}
