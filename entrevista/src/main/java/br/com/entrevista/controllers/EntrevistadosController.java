package br.com.entrevista.controllers;

import java.io.IOException;
import java.util.List;

import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;

import br.com.entrevista.daos.DaoUsuario;
import br.com.entrevista.models.Entrevista;
import br.com.entrevista.models.EnumTipoUsuario;
import br.com.entrevista.models.Usuario;

@Controller
@RequestScope
public class EntrevistadosController {


	@Autowired
	private DaoUsuario daoUsuario;

	private Entrevista entrevista  = new Entrevista();
	private Usuario entrevistadoSeleciondo = new Usuario();
	
	public Entrevista getEntrevista() {
		return entrevista;
	}
	public void setEntrevista(Entrevista entrevista) {
		this.entrevista = entrevista;
	}
	
	public Usuario getEntrevistadoSeleciondo() {
		return entrevistadoSeleciondo;
	}
	public void setEntrevistadoSeleciondo(Usuario entrevistadoSeleciondo) {
		this.entrevistadoSeleciondo = entrevistadoSeleciondo;
	}
	
	
	public List<Usuario> getEntrevistados(){	
		List<Usuario> usuarios = this.daoUsuario.findAllUsuariosEntrevistados(EnumTipoUsuario.ENTREVISTADO);
		return usuarios;
		
	}
	
	public void redirecionarEntrevista(SelectEvent event ){		
		FacesContext faces = FacesContext.getCurrentInstance();
		try {
			faces.getExternalContext().getSessionMap().put("entrevistadoSelecionado", this.entrevistadoSeleciondo);
			faces.getExternalContext().redirect("entrevista.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	

	
	
	




}
