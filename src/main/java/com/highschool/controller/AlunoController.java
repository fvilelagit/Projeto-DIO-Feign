package com.highschool.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.highschool.entities.Aluno;
import com.highschool.service.AlunoServiceImpl;

@RestController
@RequestMapping("alunos")
public class AlunoController {

	@Autowired
	private AlunoServiceImpl alunoService;

	@PostMapping
	public ResponseEntity<Aluno> inserir(@RequestBody Aluno aluno) {
		alunoService.salvar(aluno);
		return ResponseEntity.ok(aluno);
	}
	
	@GetMapping
	public ResponseEntity<List<Aluno>> findAll() {
		return ResponseEntity.ok(alunoService.acharTodos());
	}

	@GetMapping("/endereco/{cep}")
	public ResponseEntity<List<Optional<Aluno>>> findAllByEnderecoCep(@PathVariable String cep) {
		return ResponseEntity.ok(alunoService.acharPorCep(cep));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Aluno> acharPorId(@PathVariable Long id) {
		return ResponseEntity.ok(alunoService.acharPorId(id));
	}


	@PutMapping("/{id}")
	public ResponseEntity<Aluno> atualizar(@PathVariable Long id, @RequestBody Aluno aluno) {
		alunoService.atualizar(id, aluno);
		return ResponseEntity.ok(aluno);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		alunoService.deletar(id);
		return ResponseEntity.ok().build();
	} 
}