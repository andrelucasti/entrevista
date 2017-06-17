package br.com.entrevista.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cargo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "descricao")
	@Enumerated(EnumType.STRING)
	private EnumCargo enumCargo;
	
	@OneToMany(mappedBy = "cargo")
	private List<Entrevista> entrevistas = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Entrevista> getEntrevistas() {
		return entrevistas;
	}

	public void setEntrevistas(List<Entrevista> entrevistas) {
		this.entrevistas = entrevistas;
	}
	
	public EnumCargo getEnumCargo() {
		return enumCargo;
	}
	
	public void setEnumCargo(EnumCargo enumCargo) {
		this.enumCargo = enumCargo;
	}

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		
		boolean retorno = false;
		
		if (obj instanceof Cargo) {
			if (((Cargo)obj).getId().equals(this.getId())) {
				retorno =  true;
			} 
		}
		
		return retorno;
	}
	
	
	
	
	
	
	
	

}
