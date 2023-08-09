package br.ce.wcaquino.barriga.infra;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.ce.wcaquino.barriga.dominio.Usuario;
import br.ce.wcaquino.barriga.service.repositories.UsuarioRepository;

public class UsuarioMemoryRepository implements UsuarioRepository{
	
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	
	private Long autoincremento;

	public UsuarioMemoryRepository() {
		super();
		usuarios = new ArrayList<Usuario>();
		autoincremento = 0L;
		salvar(new Usuario(null, "Usuario 1", "user@mail.com", "123456"));
	}

	@Override
	public Usuario salvar(Usuario usuario) {
		
		Usuario newUsuario = new Usuario(++autoincremento, usuario.nome(), usuario.email(), usuario.senha());
		
		usuarios.add(newUsuario);
		
		return newUsuario;
		
	}

	@Override
	public Optional<Usuario> getUsuarioPorEmail(String email) {
		return usuarios.stream().filter(u -> u.email().equalsIgnoreCase(email)).findFirst();
	}

}
