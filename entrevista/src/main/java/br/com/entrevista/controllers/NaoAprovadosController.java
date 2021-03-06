
package br.com.entrevista.controllers;

import java.io.IOException;
import java.util.List;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.RequestScope;

import br.com.entrevista.models.Entrevista;
import br.com.entrevista.models.Usuario;
import br.com.entrevista.service.EntrevistaService;
import br.com.entrevista.service.UsuarioService;

@Controller
@RequestScope
public class NaoAprovadosController {

	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private EntrevistaService entrevistaService;
	
	
	
	public List<Usuario> getListaNaoAprovados(){
		List<Usuario> naoAprovados = this.usuarioService.findAllUsuariosNaoAprovados();
		
		return naoAprovados;
	}
	
	
	public void editarEntrevista(Usuario pUsuario){
		FacesContext faces = FacesContext.getCurrentInstance();
		Entrevista entrevista = new Entrevista();
		entrevista = this.entrevistaService.findEntrevistaByUsuario(pUsuario);
		
		faces.getExternalContext().getSessionMap().put("entrevistadoSelecionado", entrevista.getUsuario());

		try {
			faces.getExternalContext().redirect("entrevista.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	@Transactional
	public void removerEntrevista(Usuario pUsuario){
		FacesContext faces = FacesContext.getCurrentInstance();
		Entrevista entrevista = new Entrevista();
		entrevista = this.entrevistaService.findEntrevistaByUsuario(pUsuario);
		
		this.entrevistaService.delete(entrevista);
		
		try {
			faces.getExternalContext().redirect("entrevistados.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
