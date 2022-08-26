package com.aula.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aula.entidades.Compromisso;

@Repository
public interface CompromissoRepository extends JpaRepository<Compromisso, Long>{
	@Query("select cp from Compromisso cp, ContatoEntidade ct where cp.contato = ct.id and ct.nome = ?1")
	List<Compromisso> consultaCompromissoPorNomeContato(String nome);
}
