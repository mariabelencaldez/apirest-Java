package com.aula.entidades;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

@Entity
public class Compromisso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private ContatoEntidade contato;
		
	@NotBlank(message ="O local deve ser informado")
	private String local;
	@Temporal(TemporalType.DATE)
	private Date data;


	public Compromisso() {
		}

	public Compromisso(@NotBlank(message = "O local deve ser informado") String local, Date data,
			ContatoEntidade contato) {
		super();
		this.local = local;
		this.data = data;
		this.contato = contato;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public ContatoEntidade getContato() {
		return contato;
	}

	public void setContato(ContatoEntidade contato) {
		this.contato = contato;
	}
	
	
	
}
