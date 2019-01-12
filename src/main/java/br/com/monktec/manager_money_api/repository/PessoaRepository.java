package br.com.monktec.manager_money_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.monktec.manager_money_api.model.pessoa.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
