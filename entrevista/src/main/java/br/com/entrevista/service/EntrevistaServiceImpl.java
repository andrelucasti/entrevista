package br.com.entrevista.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.entrevista.daos.DaoEntrevista;
import br.com.entrevista.models.Entrevista;
import br.com.entrevista.models.Usuario;

@Transactional
@Service
public class EntrevistaServiceImpl implements EntrevistaService{

	@Autowired
	private DaoEntrevista daoEntrevista;
	
	
	@Override
	public void save(Entrevista pEntrevista) {
		this.daoEntrevista.save(pEntrevista);
		
	}

	@Override
	public void delete(Entrevista pEntrevista) {
		this.daoEntrevista.delete(pEntrevista);
	}

	@Override
	public Entrevista findEntrevistaByUsuario(Usuario pUsuario) {
		return this.daoEntrevista.findEntrevista(pUsuario.getId());
	}

	
}
