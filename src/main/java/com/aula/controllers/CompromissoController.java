package com.aula.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aula.entidades.Compromisso;
import com.aula.repository.CompromissoRepository;
import com.aula.services.CompromissoService;

@RestController
@RequestMapping("/")
public class CompromissoController {
	@Autowired
	CompromissoService service;
	
	CompromissoRepository repo;
	
	@PostMapping("/compromissos")
	public ResponseEntity<Compromisso> saveCompromisso(@RequestBody Compromisso compromisso){
		return ResponseEntity.ok(service.salvarCompromisso(compromisso));
	}

	@GetMapping("/compromissos")
	public ResponseEntity<List<Compromisso>> getCompromissos(){
		return ResponseEntity.ok(service.consultarCompromissos());
	}
	
	
	@GetMapping("/compromissos/contato/{nome}")
	public ResponseEntity<List <Compromisso>> consultaCompromissoPorNomeContato(@PathVariable("nome") String nome){
		return ResponseEntity.ok(service.consultarCompromissoPeloNome(nome));
	}
	
}
