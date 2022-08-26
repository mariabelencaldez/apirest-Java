package com.aula.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.aula.entidades.Compromisso;
import com.aula.entidades.ContatoEntidade;
import com.aula.repository.CompromissoRepository;
import com.aula.services.CompromissoService;

@ExtendWith(SpringExtension.class)
public class CompromissoServiceTestes {

	Compromisso compromisso;
	Compromisso compromissoValido;
	Compromisso compromissoInvalido;

	ContatoEntidade contato;
	ContatoEntidade contatoValido;
	ContatoEntidade contatoInvalido;
	
	List<Compromisso> listaCompromisso;
	List<Compromisso> listaCompromissoInvalido;
		
	@BeforeEach
	void setup() {
		contatoValido = new ContatoEntidade("maria","maria@gmail.com","(47)9917-29376");
		contatoInvalido = new ContatoEntidade("","mariagmail.com","(47)9917-2937");
		
		compromissoValido = new Compromisso();
		compromissoInvalido = new Compromisso();
		compromissoInvalido.setContato(contatoInvalido);
		
		listaCompromisso = new ArrayList<>();
		
				
		Mockito.when(repository.save(compromissoValido)).thenReturn(compromissoValido);
		Mockito.doThrow(IllegalArgumentException.class).when(repository).save(compromissoInvalido);
		
		Mockito.when(repository.findAll()).thenReturn(listaCompromisso);
		
		Mockito.when(repository.consultaCompromissoPorNomeContato(contatoValido.getNome())).thenReturn(listaCompromisso);
		Mockito.doThrow(EntityNotFoundException.class).when(repository).consultaCompromissoPorNomeContato(contatoInvalido.getNome());
	}
	
	@InjectMocks
	CompromissoService service;
	
	@Mock
	CompromissoRepository repository;
	
	@Test	
	public void retornaCompromissoQuandoSalvaComSucesso() {
		Compromisso compromisso = service.salvarCompromisso(compromissoValido);
		Assertions.assertNotNull(compromisso);
		Mockito.verify(repository).save(compromissoValido);
	}
	
	@Test
	public void retornaExcecaoAoQuandoSalvarSemSucesso () {
		Assertions.assertThrows(IllegalArgumentException.class, () -> service.salvarCompromisso(compromissoInvalido));
		Mockito.verify(repository).save(compromissoInvalido);
		}
	
	@Test
	public void retornaListaDeCompromissosComSucesso() {
		List<Compromisso> compromissos = service.consultarCompromissos();
		Assertions.assertNotNull(compromissos);
		Mockito.verify(repository).findAll();
	}
	
	@Test
	public void retornaCompromissoPorNomeSemSucesso() {
		Assertions.assertThrows(EntityNotFoundException.class, () -> {
			service.consultarCompromissoPeloNome(contatoInvalido.getNome());
		});
		Mockito.verify(repository, Mockito.times(1)).consultaCompromissoPorNomeContato(contatoInvalido.getNome());
	}
	
	
	
	
}
