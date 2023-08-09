package br.ce.wcaquino.barriga.dominio.builders;

import br.ce.wcaquino.barriga.dominio.Conta;
import br.ce.wcaquino.barriga.dominio.Usuario;


public class ContaBuilder {
	
	private Long id;
	
	private String nome;
	
	private Usuario usuario;
	
	private ContaBuilder(){}

	public static ContaBuilder umaConta() {
		ContaBuilder builder = new ContaBuilder();
		inicializarDadosPadroes(builder);
		return builder;
	}

	public static void inicializarDadosPadroes(ContaBuilder builder) {
		builder.id = 1L;
		builder.nome = "Conta Válida";
		builder.usuario = UsuarioBuilder.umUsuario().agora();
	}

	public ContaBuilder comId(Long param) {
		id = param;
		return this;
	}

	public ContaBuilder comNome(String param) {
		nome = param;
		return this;
	}

	public ContaBuilder comUsuario(Usuario param) {
		usuario = param;
		return this;
	}

	public Conta agora() {
		return  new Conta(id,nome,usuario);
	}
}
