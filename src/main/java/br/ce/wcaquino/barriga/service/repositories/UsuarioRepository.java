package br.ce.wcaquino.barriga.service.repositories;

import java.util.Optional;

import br.ce.wcaquino.barriga.dominio.Usuario;

public interface UsuarioRepository {
	
	Usuario salvar(Usuario usuario);
	
	Optional<Usuario> getUsuarioPorEmail(String email);

}
