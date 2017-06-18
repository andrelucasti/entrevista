package br.com.entrevista.controllers;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;

import br.com.entrevista.models.EnumTipoUsuario;
import br.com.entrevista.models.TipoUsuario;
import br.com.entrevista.models.Usuario;
import br.com.entrevista.service.UsuarioService;

@Controller
@RequestScope
public class UsuarioController {


	@Autowired
	private UsuarioService usuarioService;

	

	@PostConstruct
	private void initi() {
		this.getObjectsRequest();

	}

	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	private void getObjectsRequest(){

		if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("entrevistadoSelecionado")) {

			usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("entrevistadoSelecionado");

		} 
		
		if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("usuarioEntrevistadoLogado")) {

			usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioEntrevistadoLogado");

		} 



	}



	public String salvar(){
		FacesContext faces = FacesContext.getCurrentInstance();
		faces.getExternalContext().getFlash().setKeepMessages(true);

		TipoUsuario tipoUsuario = this.usuarioService.findTipoUsuario(EnumTipoUsuario.ENTREVISTADO);

		this.usuario.setTipoUsuarios(Arrays.asList(tipoUsuario));

		try {

			usuarioService.save(this.usuario);	
			faces.addMessage("msgSuccess", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "O usuário: "+usuario.getEmail()+" foi cadastrado."));

		} catch (DataIntegrityViolationException e) {
			faces.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!", "Usuário ja cadastrado"));

		} finally {
			this.usuario = new Usuario();			
		}

		return "login.xhtml?faces-redirect=true";
	}


	public String salvarEntrevistador(){
		FacesContext faces = FacesContext.getCurrentInstance();
		faces.getExternalContext().getFlash().setKeepMessages(true);

		TipoUsuario tipoUsuario = this.usuarioService.findTipoUsuario(EnumTipoUsuario.ENTREVISTADOR);

		this.usuario.setTipoUsuarios(Arrays.asList(tipoUsuario));

		try {

			usuarioService.save(this.usuario);	
			faces.addMessage("msgSuccess", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "O usuário: "+usuario.getEmail()+" foi cadastrado."));

		} catch (DataIntegrityViolationException e) {
			faces.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!", "Usuário ja cadastrado"));

		} finally {
			this.usuario = new Usuario();			
		}

		return "entrevistados.xhtml?faces-redirect=true";
	}	



}
