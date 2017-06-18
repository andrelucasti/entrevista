package br.com.entrevista.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.entrevista.daos.DaoTipoUsuario;
import br.com.entrevista.daos.DaoUsuario;
import br.com.entrevista.models.EnumTipoUsuario;
import br.com.entrevista.models.TipoUsuario;
import br.com.entrevista.models.Usuario;

@Transactional
@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private DaoUsuario daoUsuario;

	@Autowired
	private DaoTipoUsuario daoTipoUsuario;

	@Override
	public void save(Usuario pUsuario) {
		this.daoUsuario.save(pUsuario);
		
	}

	@Override
	public void delete(Usuario pUsuario) {
		this.daoUsuario.delete(pUsuario);
	}


	@Override
	public Usuario findByLogin(String pLogin) {
		
		return this.daoUsuario.findUsuarioByLogin(pLogin);
	}

	@Override
	public List<Usuario> findAll() {
		return (List<Usuario>) this.daoUsuario.findAll();
	}

	@Override
	public List<Usuario> findAllUsuariosEntrevistados(EnumTipoUsuario pTipoUsuario) {
		return this.daoUsuario.findAllUsuariosEntrevistados(pTipoUsuario);
	}

	@Override
	public List<Usuario> findAllUsuariosAprovados() {
		return this.findAllUsuariosAprovados();
	}

	@Override
	public List<Usuario> findAllUsuariosNaoAprovados() {
		return this.findAllUsuariosNaoAprovados();
	}

	@Override
	public List<TipoUsuario> findAllTipoUsuario() {
		return (List<TipoUsuario>) this.daoTipoUsuario.findAll();
	}

	@Override
	public TipoUsuario findTipoUsuario(EnumTipoUsuario pTipoUsuario) {
		return this.daoTipoUsuario.findByName(pTipoUsuario);
	}

}
