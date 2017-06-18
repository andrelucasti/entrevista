package br.com.entrevista.service;

import br.com.entrevista.models.Entrevista;
import br.com.entrevista.models.Usuario;

public interface EntrevistaService {
	
	public void save(Entrevista pEntrevista);
	public void delete(Entrevista pEntrevista);
	public Entrevista findEntrevistaByUsuario(Usuario pUsuario);

	
}
