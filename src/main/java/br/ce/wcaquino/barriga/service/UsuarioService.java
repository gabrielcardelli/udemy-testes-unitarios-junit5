package br.ce.wcaquino.barriga.service;

import br.ce.wcaquino.barriga.dominio.Usuario;
import br.ce.wcaquino.barriga.dominio.exceptions.ValidationException;
import br.ce.wcaquino.barriga.service.repositories.UsuarioRepository;

public class UsuarioService {
	
	private UsuarioRepository usuarioRepository;
	
	public UsuarioService(UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}

	public Usuario salvar(Usuario usuario) {
	
		usuarioRepository.getUsuarioPorEmail(usuario.email()).ifPresent(u -> {
			throw new ValidationException(String.format("Usuário %s já cadastrado", usuario.email()));
		});
		
		return usuarioRepository.salvar(usuario);
	}

}
