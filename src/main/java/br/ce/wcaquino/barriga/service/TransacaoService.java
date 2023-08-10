package br.ce.wcaquino.barriga.service;

import java.time.LocalDateTime;
import java.util.Date;

import br.ce.wcaquino.barriga.dominio.Transacao;
import br.ce.wcaquino.barriga.dominio.exceptions.ValidationException;
import br.ce.wcaquino.barriga.service.external.ClockService;
import br.ce.wcaquino.barriga.service.repositories.TransacaoDao;

public class TransacaoService {
	
	private TransacaoDao transacaoDao;
	
	private ClockService clock;
	
	public Transacao salvar( Transacao transacao ) {
		
		if(getTime().getHour() > 15) {
			throw new RuntimeException("Tente novamente amanhã");
		}
		
		if(transacao.getDescricao() == null) throw new ValidationException("Descrição inexistente");
		if(transacao.getValor() == null) throw new ValidationException("Valor inexistente");
		if(transacao.getData() == null) throw new ValidationException("Data inexistente");
		if(transacao.getConta() == null) throw new ValidationException("Conta inexistente");
		if(transacao.getStatus() == null) transacao.setStatus(false);
		
		return transacaoDao.salvar(transacao);
		
	}
	
	protected LocalDateTime getTime() {
		return LocalDateTime.now();
	}

}
