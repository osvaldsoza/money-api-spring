package br.com.monktec.manager_money_api.service;

import br.com.monktec.manager_money_api.model.lancamento.Lancamento;
import br.com.monktec.manager_money_api.model.pessoa.Pessoa;
import br.com.monktec.manager_money_api.repository.LancamentoRepository;
import br.com.monktec.manager_money_api.repository.PessoaRepository;
import br.com.monktec.manager_money_api.model.lancamento.LancamentoFilter;
import br.com.monktec.manager_money_api.service.exception.PessoaInexistenteOuInativaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Page<Lancamento> listarLancamentosPorFiltros(LancamentoFilter lancamentoFilter, Pageable pageable){
        return lancamentoRepository.filtrar(lancamentoFilter,pageable);
    }

    public Lancamento buscarLancamentoPeloCodigo(Long codigo){
        return lancamentoRepository.findOne(codigo);
    }

    public Lancamento salvarLancamento(Lancamento lancamento){

        Pessoa pessoa = pessoaRepository.findOne(lancamento.getPessoa().getCodigo());

        if( pessoa == null || pessoa.isInativa()){
            throw new PessoaInexistenteOuInativaException();
        }
        return lancamentoRepository.save(lancamento);
    }

    public void deletarLancamneto(Long codigo){
        lancamentoRepository.delete(codigo);
    }
}
