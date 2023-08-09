package br.ce.wcaquino.barriga.dominio.builders;

import br.ce.wcaquino.barriga.dominio.Usuario;

public class UsuarioBuilder {
	
	private Long id;
	
	private String nome;
	
	private String email;
	
	private String senha;
	
	private UsuarioBuilder() {}
	
	public static UsuarioBuilder umUsuario() {
		UsuarioBuilder builder = new UsuarioBuilder();
		inicializarDadosPadroes(builder);
		return builder;
	}

	private static void inicializarDadosPadroes(UsuarioBuilder builder) {
		builder.id = 1L;
		builder.nome = "Usuário Válido";
		builder.email = "user@mail.com";
		builder.senha = "12345678";
	}
	
	public  UsuarioBuilder comId(Long param) {
		this.id = param;
		return this;
	}
	
	public  UsuarioBuilder comNome(String param) {
		this.nome = param;
		return this;
	}
	
	public  UsuarioBuilder comEmail(String param) {
		this.email = param;
		return this;
	}
	
	public  UsuarioBuilder comSenha(String param) {
		this.senha = param;
		return this;
	}
	
	public Usuario agora() {
		return new Usuario(id,nome,email,senha);
	}

}
