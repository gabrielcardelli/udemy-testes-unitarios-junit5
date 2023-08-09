package br.ce.wcaquino.barriga.infra;

import java.util.Optional;

import br.ce.wcaquino.barriga.dominio.Usuario;
import br.ce.wcaquino.barriga.dominio.builders.UsuarioBuilder;
import br.ce.wcaquino.barriga.service.repositories.UsuarioRepository;

public class UsuarioDummyRepository implements UsuarioRepository {

	@Override
	public Usuario salvar(Usuario usuario) {
		return UsuarioBuilder.umUsuario()
				.comId(1L)
				.comEmail(usuario.email())
				.comNome(usuario.nome())
				.comSenha(usuario.senha()).agora();
	}

	@Override
	public Optional<Usuario> getUsuarioPorEmail(String email) {
		
		if("user@mail.com".equals(email))
			return Optional.of(UsuarioBuilder.umUsuario().comEmail(email).agora());
		else
			return Optional.empty();
		
	}

}
