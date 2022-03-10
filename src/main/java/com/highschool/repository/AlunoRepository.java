package com.highschool.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.highschool.entities.Aluno;


public interface AlunoRepository extends JpaRepository<Aluno,Long>{

	List<Optional<Aluno>> findAllByEnderecoCep(String cep);

}
