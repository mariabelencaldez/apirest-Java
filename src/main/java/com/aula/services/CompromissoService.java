package com.aula.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aula.entidades.Compromisso;
import com.aula.repository.CompromissoRepository;

@Service
public class CompromissoService {
	
	@Autowired
	CompromissoRepository repo;
	
	
	public Compromisso salvarCompromisso (Compromisso compromisso) {
		return repo.save(compromisso);
	}
	
	public List<Compromisso> consultarCompromissos(){
		List<Compromisso> compromisso = repo.findAll();
		return compromisso;
	}
	
	public List<Compromisso> consultarCompromissoPeloNome (String nome){
		return repo.consultaCompromissoPorNomeContato(nome);
	}
}
