package br.com.entrevista.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.entrevista.models.Cargo;
import br.com.entrevista.service.UsuarioService;
/**
 * 
 * @author Andre
 *	
 * Responsável por converter o ID do meu objeto {@link Cargo} para String
 *	
 *
 */
@Component
public class CargoConverter implements Converter {
	
	@Autowired
	private UsuarioService daoCargo;
	
	/**
	 * Recebe a string da view e transforma para o objeto 
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		if (value.equals("")) {
			
			return new Cargo();
		} else {
		
			return daoCargo.findCargo(Integer.valueOf(value));
		}
	}
	
	/**
	 * @author Andre
	 * 
	 * String retornada será utilizada pelo componente JSF para ser exibida na tela do usuário.
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (value instanceof String) {
			return (String) value;
		} else {
		
			Cargo c = (Cargo) value;
			return c.getId().toString();
		}
	}

}
