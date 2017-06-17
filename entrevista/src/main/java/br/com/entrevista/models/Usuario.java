package br.com.entrevista.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY )
	private Integer id;
	
	@Column(nullable = false)
	private String nome;

	
	@Column(nullable = false, unique= true)
	private String login;
	

	@Column(nullable = false, unique= true)
	private String email;
	

	@Column(nullable = false)
	private String senha;
	
	@ManyToMany
	@JoinTable(name="usuario_tipo_usuario")
	private List<TipoUsuario> tipoUsuarios;
	
	@OneToOne(mappedBy = "usuario")
	private Entrevista entrevista;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public Entrevista getEntrevista() {
		return entrevista;
	}


	public void setEntrevista(Entrevista entrevista) {
		this.entrevista = entrevista;
	}


	public List<TipoUsuario> getTipoUsuarios() {
		return tipoUsuarios;
	}


	public void setTipoUsuarios(List<TipoUsuario> tipoUsuarios) {
		this.tipoUsuarios = tipoUsuarios;
	}


	
	
	
	
	
	
}
