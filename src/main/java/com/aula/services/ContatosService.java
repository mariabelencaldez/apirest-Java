package com.aula.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.aula.entidades.ContatoEntidade;
import com.aula.repository.ContatoRepository;
import com.aula.services.exceptions.ValidacaoException;

@Service
public class ContatosService {
	
	@Autowired
	ContatoRepository repo;
	
	public ContatoDTO salvar (ContatoEntidade contato) {
		/*if(!contato.getEmail().contains("@")) {
			throw new ValidacaoException("Email invalido");
		}
		
		if(contato.getFone().length()!= 14) {
			throw new ValidacaoException("Telefone invalido");
		}*/
		
		ContatoEntidade ct = repo.save(contato);
		
		ContatoDTO contatoDTO = new ContatoDTO(ct);
		
		return contatoDTO;
	}
	
	public List<ContatoDTO> consultarContatos(){
		List <ContatoEntidade> contatos = repo.findAll();
		List <ContatoDTO> contatoDTO = new ArrayList<>();
		for (ContatoEntidade ct : contatos) {
			contatoDTO.add(new ContatoDTO(ct));
		}
		return contatoDTO;
	}
	
	
	
	public ContatoDTO consultarContatoPorId(Long idcontato) {
		Optional<ContatoEntidade> op = repo.findById(idcontato);
		ContatoEntidade ct = op.orElseThrow(()-> new EntityNotFoundException("Contato não encontrado"));
		return new ContatoDTO(ct);
	}
	
	public ContatoEntidade consultarContato(Long idcontato) {
		Optional<ContatoEntidade> op = repo.findById(idcontato);
		ContatoEntidade ct = op.orElseThrow(()-> new EntityNotFoundException("Contato não encontrado"));
		return ct;
	}
	
	
	public void excluirContato(Long idcontato) {
		
		repo.deleteById(idcontato);
	}
	
	public ContatoDTO alterarContato(Long id, ContatoEntidade contato) {
		
		ContatoEntidade ct = consultarContato(id);
		BeanUtils.copyProperties(contato, ct, "id");
		
		//ct.setEmail(contato.getEmail());
		//ct.setNome(contato.getNome());
		//ct.setFone(contato.getFone());
		
		return new ContatoDTO(repo.save(ct));
	}
	
	public List <ContatoDTO> consultarContatoPorEmail(String email){
		return ContatoDTO.converteParaDTO(repo.findByEmail(email));
	}
}
