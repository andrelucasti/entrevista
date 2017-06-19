package br.com.entrevista.controllers;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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


	private Usuario usuarioEntrevistado = new Usuario();
	private Usuario usuarioEntrevistador = new Usuario();
	private Usuario usuarioLogado = new Usuario();
	
	


	public Usuario getUsuarioEntrevistado() {
		return usuarioEntrevistado;
	}


	public void setUsuarioEntrevistado(Usuario usuarioEntrevistado) {
		this.usuarioEntrevistado = usuarioEntrevistado;
	}


	public Usuario getUsuarioEntrevistador() {
		return usuarioEntrevistador;
	}


	public void setUsuarioEntrevistador(Usuario usuarioEntrevistador) {
		this.usuarioEntrevistador = usuarioEntrevistador;
	}


	@PostConstruct
	private void init() {
		this.getUsuarioSelecionado();
		this.getUsuarioEntrevistadoLogado();

	}
	
	
	private void getUsuarioSelecionado(){
		FacesContext faces = FacesContext.getCurrentInstance();
		
		Usuario entrevistadoSelecionado = (Usuario)faces.getExternalContext().getSessionMap().get("entrevistadoSelecionado");
		
		if (entrevistadoSelecionado != null) {
			this.usuarioEntrevistado = entrevistadoSelecionado;
		} else { 
			this.usuarioEntrevistado = new Usuario();
		}
		
		
	}
	
	
	private void getUsuarioEntrevistadoLogado(){
				
		if (isUsuarioLogado()) {
			TipoUsuario tipoUsuarioLogado = usuarioLogado.getTipoUsuarios().get(0);
			if (tipoUsuarioLogado.getTipoUsuario().equals(EnumTipoUsuario.ENTREVISTADO)) {
				this.usuarioEntrevistado = this.usuarioLogado;
			}
		}
		
	}

	
	@Transactional
	public String salvar(){
		FacesContext faces = FacesContext.getCurrentInstance();
		faces.getExternalContext().getFlash().setKeepMessages(true);

		TipoUsuario tipoUsuario = this.usuarioService.findTipoUsuario(EnumTipoUsuario.ENTREVISTADO);

		this.usuarioEntrevistado.setTipoUsuarios(Arrays.asList(tipoUsuario));

		try {

			usuarioService.save(this.usuarioEntrevistado);	
			faces.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "O usuário: "+usuarioEntrevistado.getEmail()+" foi cadastrado."));

		} catch (DataIntegrityViolationException e) {
			faces.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!", "Usuário ja cadastrado"));

		} finally {
			faces.getExternalContext().getSessionMap().remove("entrevistadoSelecionado");
			this.usuarioEntrevistado = new Usuario();			
		}
		
		
		if (isUsuarioLogado()) {
			TipoUsuario tipoUsuarioLogado = usuarioLogado.getTipoUsuarios().get(0);
			
			if (tipoUsuarioLogado.getTipoUsuario().equals(EnumTipoUsuario.ENTREVISTADOR)) {
				return "entrevistados.xhtml?faces-redirect=true";
			} else {
				return "login.xhtml?faces-redirect=true";
				
			}
		} else {
			return "login.xhtml?faces-redirect=true";
		}
		
		
		
		

	}

	@Transactional
	public String salvarEntrevistador(){
		FacesContext faces = FacesContext.getCurrentInstance();
		faces.getExternalContext().getFlash().setKeepMessages(true);

		TipoUsuario tipoUsuario = this.usuarioService.findTipoUsuario(EnumTipoUsuario.ENTREVISTADOR);

		this.usuarioEntrevistador.setTipoUsuarios(Arrays.asList(tipoUsuario));

		try {

			usuarioService.save(this.usuarioEntrevistador);	
			faces.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "O usuário: "+usuarioEntrevistador.getEmail()+" foi cadastrado."));

		} catch (DataIntegrityViolationException e) {
			faces.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!", "Usuário ja cadastrado"));

		} finally {
			this.usuarioEntrevistador = new Usuario();			
		}

		return "entrevistados.xhtml?faces-redirect=true";
	}	


	
	private boolean isUsuarioLogado(){
		Usuario usuarioLogado  = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
		
		if (usuarioLogado != null) {
			this.usuarioLogado = usuarioLogado;
			
			return true;
		} else {
			return false;
		}
	}

}
