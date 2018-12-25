package br.com.monktec.manager_money_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.monktec.manager_money_api.model.Pessoa;
import br.com.monktec.manager_money_api.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public List<Pessoa> listarPessoas(){
		return pessoaRepository.findAll();
	}
	
	public Pessoa salvarPessoa(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}
	
	public Pessoa buscarPeloCodigo(long codigo) {
		return pessoaRepository.findOne(codigo);
	}

}
