package br.com.entrevista.controllers;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;

import br.com.entrevista.daos.DaoUsuario;
import br.com.entrevista.models.Usuario;

@Controller
@RequestScope
public class UsuarioController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private DaoUsuario daoUsuario;
	
	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	public String salvar(){
		FacesContext faces = FacesContext.getCurrentInstance();
		faces.getExternalContext().getFlash().setKeepMessages(true);
		
		try {
			daoUsuario.save(this.usuario);	
			faces.addMessage("msgSuccess", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "O usuário: "+usuario.getEmail()+" foi cadastrado."));
			    
		} catch (DataIntegrityViolationException e) {
			faces.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!", "Usuário ja cadastrado"));
		     
		} finally {
			this.usuario = new Usuario();			
		}
		
		return "index?faces-redirect=true";
	}
	
	
		
	
	
}
