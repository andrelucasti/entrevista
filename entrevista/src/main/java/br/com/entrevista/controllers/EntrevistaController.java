package br.com.entrevista.controllers;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;

import br.com.entrevista.daos.DaoCargo;
import br.com.entrevista.models.Cargo;
import br.com.entrevista.models.Entrevista;
import br.com.entrevista.models.Usuario;
import br.com.entrevista.service.EntrevistaService;

@Controller
@RequestScope
public class EntrevistaController {


	@Autowired
	private EntrevistaService entrevistaService;

	@Autowired
	private DaoCargo daoCargo;

	private Entrevista entrevista  = new Entrevista();
	private Usuario usuario = new Usuario();
	private List<SelectItem> itens = Arrays.asList(new SelectItem(Boolean.TRUE, "Sim"), new SelectItem(Boolean.FALSE, "Não"));


	@PostConstruct
	public void init() {
		this.getEntrevistaByUsuario();

	}

	public Entrevista getEntrevista() {
		return entrevista;
	}
	public void setEntrevista(Entrevista entrevista) {
		this.entrevista = entrevista;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<SelectItem> getItens() {
		return itens;
	}

	public void setItens(List<SelectItem> itens) {
		this.itens = itens;
	}

	public List<Cargo> getCargos(){
		List<Cargo>cargos = (List<Cargo>) this.daoCargo.findAll();

		return cargos;
	}

	
	
	@Transactional
	public String salvar(){
		FacesContext faces = FacesContext.getCurrentInstance();
		faces.getExternalContext().getFlash().setKeepMessages(true);

		try {	

			this.entrevista.setUsuario(usuario);
			this.entrevistaService.save(this.entrevista);

			faces.getExternalContext().getSessionMap().remove("entrevistadoSelecionado");	
			faces.getExternalContext().getSessionMap().remove("entrevista");	
			faces.addMessage("msgSuccess", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Entrevista adicionada..."));
			return "entrevistados?faces-redirect=true";

		} catch (Exception e) {
			e.printStackTrace();
			faces.addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha!", e.getMessage()));
			return "entrevista?faces-redirect=true";
		}


	}



	private void getEntrevistaByUsuario(){
		
		if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("entrevistadoSelecionado")) {

			this.usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("entrevistadoSelecionado");

			Entrevista entrevista =  this.entrevistaService.findEntrevistaByUsuario(usuario);
			
			if (entrevista != null) {
				this.entrevista = entrevista;
			}

		}

	}








}
