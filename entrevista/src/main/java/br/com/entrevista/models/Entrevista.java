package br.com.entrevista.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Entrevista: Entrevistador (Usuário), Entrevistado (Usuário), 
 * Cargo em que o entrevistado se encaixa 
 * (Estagiário, Trainee, Junior, Pleno, Sênior), 
 * Descrição da vaga, Data, Comentários, Aprovado (boolean) e possibilidade de anexar o currículo;
 * @author andre.s-silva
 *
 */

@Entity
public class Entrevista {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String descricao;
	
	@Column
	private String comentario;
	
	@Column(nullable = false)
	private Boolean isAprovado;
	
	@Column
	private String pathCurriculo;

	@OneToOne
	private Usuario usuario;
	
	@ManyToOne
	private Cargo cargo;
	
	@DateTimeFormat
	private Date data;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Boolean getIsAprovado() {
		return isAprovado;
	}

	public void setIsAprovado(Boolean isAprovado) {
		this.isAprovado = isAprovado;
	}

	public String getPathCurriculo() {
		return pathCurriculo;
	}

	public void setPathCurriculo(String pathCurriculo) {
		this.pathCurriculo = pathCurriculo;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	
	

}
