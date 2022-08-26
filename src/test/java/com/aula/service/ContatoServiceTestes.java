package com.aula.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.aula.entidades.ContatoEntidade;
import com.aula.repository.ContatoRepository;
import com.aula.services.ContatoDTO;
import com.aula.services.ContatosService;

@ExtendWith(SpringExtension.class)
public class ContatoServiceTestes {
	
	private Long idExistente;
	private Long idNaoExistente;
	ContatoEntidade contato;
	ContatoEntidade contatoValido;
	ContatoEntidade contatoInvalido;


	ContatoDTO contatoDTO;
	
	
	@BeforeEach
	void setup() {
		idExistente = 1l;
		idNaoExistente = 1000l;
		contatoValido = new ContatoEntidade("maria","maria@gmail.com","(47)9917-29376");
		contatoInvalido = new ContatoEntidade("maria","mariagmail.com","(47)99172937");
		
		Mockito.when(repository.save(contatoValido)).thenReturn(contatoValido);
		Mockito.doThrow(IllegalArgumentException.class).when(repository).save(contatoInvalido);

		Mockito.doNothing().when(repository).deleteById(idExistente);
		Mockito.doThrow(EntityNotFoundException.class).when(repository).deleteById(idNaoExistente);
		
		Mockito.when(repository.findById(idExistente)).thenReturn(Optional.of(new ContatoEntidade()));
		Mockito.doThrow(EntityNotFoundException.class).when(repository).findById(idNaoExistente);

	}
	
	@InjectMocks
	ContatosService service;
	
	@Mock
	ContatoRepository repository;
	
	@Test
	public void retornaContatoDTOquandoSalvarComSucesso() {
		ContatoDTO contatoDTO = service.salvar(contatoValido);
		Assertions.assertNotNull(contatoDTO);
		Mockito.verify(repository).save(contatoValido);

	}
	
	@Test
	public void retornaExcecaoAoQuandoSalvarSemSucesso () {
		Assertions.assertThrows(IllegalArgumentException.class, () -> service.salvar(contatoInvalido) );
		Mockito.verify(repository).save(contatoInvalido);
		}
	
	@Test
	public void retornaNadaAoExcluirComIdExistente() {
		Assertions.assertDoesNotThrow(()-> {
			service.excluirContato(idExistente);
		});
		Mockito.verify(repository,Mockito.times(1)).deleteById(idExistente);
	}
	
	@Test
	public void lancaEntidadeNaoEncontradaAoExcluirIdNaoExistente () {
		Assertions.assertThrows(EntityNotFoundException.class, () -> {
			service.excluirContato(idNaoExistente);
		});
		Mockito.verify(repository,Mockito.times(1)).deleteById(idNaoExistente);		
	}
	
	@Test
	public void retornaContatoQuandoPesquicaPorIdExistente() {
		ContatoDTO ct = service.consultarContatoPorId(idExistente);
		Assertions.assertNotNull(ct);
		Mockito.verify(repository).findById(idExistente);
	}

	@Test
	public void lancaEntidadeNaoEncontradaAoConsultarIdNaoExistente () {
		Assertions.assertThrows(EntityNotFoundException.class, () -> {
			service.consultarContatoPorId(idNaoExistente);
		});
		Mockito.verify(repository,Mockito.times(1)).findById(idNaoExistente);		
	}
	

}
