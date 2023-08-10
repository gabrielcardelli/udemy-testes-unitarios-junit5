package br.ce.wcaquino.barriga.service.repositories;

import java.util.List;

import br.ce.wcaquino.barriga.dominio.Conta;

public interface ContaRepository {
	
	Conta salvar(Conta conta);
	
	List<Conta> obterContasPorUsuario(Long id);
	
	void delete (Conta conta);

}
