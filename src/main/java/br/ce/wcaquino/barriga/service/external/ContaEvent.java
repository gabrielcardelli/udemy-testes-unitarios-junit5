package br.ce.wcaquino.barriga.service.external;

import br.ce.wcaquino.barriga.dominio.Conta;

public interface ContaEvent {
	public enum EventType {
		CREATED,UPDATED,DELETED
	}
	
	void dispatch(Conta conta, EventType eventType) throws Exception;
}
