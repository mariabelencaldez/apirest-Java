package com.aula.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aula.entidades.ContatoEntidade;

@Repository
public interface ContatoRepository extends JpaRepository <ContatoEntidade, Long>{
	List<ContatoEntidade> findByEmail(String email);
}
