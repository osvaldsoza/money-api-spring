package br.com.monktec.manager_money_api.repository;

import br.com.monktec.manager_money_api.model.lancamento.Lancamento;
import br.com.monktec.manager_money_api.model.lancamento.LancamentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LancamnetoRepositoryQuery {

    public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
}
