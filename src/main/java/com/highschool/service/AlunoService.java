package com.highschool.service;

import java.util.List;
import java.util.Optional;

import com.highschool.entities.Aluno;

public interface AlunoService {

	List <Aluno> acharTodos();
	void salvar(Aluno a);
	Aluno acharPorId(Long a);
	List <Optional<Aluno>> acharPorCep(String nome);
	
	
	
}
