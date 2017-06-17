package br.com.entrevista.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.entrevista.daos.DaoCargo;
import br.com.entrevista.models.Cargo;

@Component
public class CargoConverter implements Converter {
	
	@Autowired
	private DaoCargo daoCargo;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value.equals("")) {
			
			return new Cargo();
		} else {
		
			return daoCargo.findOne(Integer.valueOf(value));
		}
	}

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
