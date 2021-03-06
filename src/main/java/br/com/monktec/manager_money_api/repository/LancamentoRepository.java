package br.com.monktec.manager_money_api.repository;

import br.com.monktec.manager_money_api.model.lancamento.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamnetoRepositoryQuery {}
