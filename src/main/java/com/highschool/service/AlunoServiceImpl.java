package com.highschool.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.highschool.entities.Aluno;
import com.highschool.entities.Endereco;
import com.highschool.exceptions.ExcessaoAlunoNaoEncontrado;
import com.highschool.exceptions.ExcessaoNoBancoDeDados;
import com.highschool.repository.AlunoRepository;
import com.highschool.repository.EnderecoRepository;

@Service
public class AlunoServiceImpl implements AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ViaCepService viaCepService;
	
	
	public void salvar(Aluno a) {
		 salvarComCep(a);
	}	
	
	private void salvarComCep(Aluno aluno) {

		String cep = aluno.getEndereco().getCep();
		Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {

			Endereco novoEndereco = viaCepService.consultarCep(cep);
			enderecoRepository.save(novoEndereco);
			return novoEndereco;
		});
		
		aluno.setEndereco(endereco);
	
		alunoRepository.save(aluno);
	}

	public Aluno acharPorId(Long id) {
		Optional <Aluno> obj = alunoRepository.findById(id);
		return obj.orElseThrow(() -> new ExcessaoAlunoNaoEncontrado(id));
	}

	public List<Optional<Aluno>> acharPorCep(String cep) {
			
		return alunoRepository.findAllByEnderecoCep(cep);
	}

	public List<Aluno> acharTodos() {
		return alunoRepository.findAll();
	}
	
	public Aluno atualizar(Long id, Aluno a) {
		try {
			Aluno b = alunoRepository.getById(id);
			updateData(b,a);
			return alunoRepository.save(a);
		} catch (EntityNotFoundException e) {
			 throw new ExcessaoAlunoNaoEncontrado(id);
		}
		
	}
	public Aluno updateData(Aluno a1, Aluno a2) {
		a1.setNome(a2.getNome());
		a1.setIdade(a2.getIdade());
		a1.setEndereco(a2.getEndereco());
	
		return a1;
	}
	public void deletar(Long id) {
		try {
			alunoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ExcessaoAlunoNaoEncontrado(id);
		} catch (DataIntegrityViolationException e) {
			throw new ExcessaoNoBancoDeDados(e.getMessage());
		}

	}

}
