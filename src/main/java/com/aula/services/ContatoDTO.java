package com.aula.services;

import java.util.ArrayList;
import java.util.List;

import com.aula.entidades.ContatoEntidade;

public class ContatoDTO {
	private Long id;
	private String nome;
	private String fone;
	private String email;
	
	public ContatoDTO(Long id, String nome, String fone, String email) {
		this.id = id;
		this.nome = nome;
		this.fone = fone;
		this.email = email;
	}

	public ContatoDTO() {
	
	}
	
	public ContatoDTO(ContatoEntidade contato) {
		this.id = contato.getId();
		this.nome= contato.getNome();
		this.email= contato.getEmail();
		this.fone = contato.getFone();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public static List<ContatoDTO> converteParaDTO(List<ContatoEntidade> contatos){
		List<ContatoDTO> contatosDTO = new ArrayList<>();
		for(ContatoEntidade ct: contatos) {
			contatosDTO.add(new ContatoDTO(ct));
		}
		return contatosDTO;
	}
}
