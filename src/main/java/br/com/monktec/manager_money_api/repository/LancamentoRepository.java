package br.com.monktec.manager_money_api.repository;

import br.com.monktec.manager_money_api.model.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
}
