package br.com.entrevista.service;

import java.util.List;

import br.com.entrevista.models.EnumTipoUsuario;
import br.com.entrevista.models.TipoUsuario;
import br.com.entrevista.models.Usuario;

public interface UsuarioService {
	
	public void save(Usuario pUsuario);
	public void delete(Usuario pUsuario);
	public List<Usuario> findAll();
	public Usuario findByLogin(String pLogin);
	public List<Usuario> findAllUsuariosEntrevistados(EnumTipoUsuario pTipoUsuario);
	public List<Usuario> findAllUsuariosAprovados();
	public List<Usuario> findAllUsuariosNaoAprovados();
	public List<TipoUsuario>findAllTipoUsuario();
	public TipoUsuario findTipoUsuario(EnumTipoUsuario pTipoUsuario);
	
	

}
