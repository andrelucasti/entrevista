package br.com.entrevista.controllers;

import java.io.IOException;
import java.util.List;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;

import br.com.entrevista.daos.DaoEntrevista;
import br.com.entrevista.daos.DaoUsuario;
import br.com.entrevista.models.Entrevista;
import br.com.entrevista.models.Usuario;

@Controller
@RequestScope
public class NaoAprovadosController {
	
	@Autowired
	private DaoUsuario daoUsuario;
	@Autowired
	private DaoEntrevista daoEntrevista;
	
	
	
	public List<Usuario> getListaNaoAprovados(){
		List<Usuario> naoAprovados = this.daoUsuario.findAllUsuariosNaoAprovados();
		
		return naoAprovados;
	}
	
	
	public void editarEntrevista(Usuario pUsuario){
		FacesContext faces = FacesContext.getCurrentInstance();
		Entrevista entrevista = new Entrevista();
		entrevista = this.daoEntrevista.findEntrevista(pUsuario.getId());
		
		faces.getExternalContext().getSessionMap().put("entrevista", entrevista);
		faces.getExternalContext().getSessionMap().put("entrevistadoSelecionado", entrevista.getUsuario());

		try {
			faces.getExternalContext().redirect("entrevista.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	
	public void removerEntrevista(Usuario pUsuario){
		FacesContext faces = FacesContext.getCurrentInstance();
		Entrevista entrevista = new Entrevista();
		entrevista = this.daoEntrevista.findEntrevista(pUsuario.getId());
		
		this.daoEntrevista.delete(entrevista);
		
		try {
			faces.getExternalContext().redirect("entrevistados.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
