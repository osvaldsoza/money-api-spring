package br.com.monktec.manager_money_api.repository;

import br.com.monktec.manager_money_api.model.Lancamento;
import br.com.monktec.manager_money_api.repository.filter.LancamentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LancamnetoRepositoryQuery {

    public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
}
