package br.com.monktec.manager_money_api.service;

import br.com.monktec.manager_money_api.model.Lancamento;
import br.com.monktec.manager_money_api.model.Pessoa;
import br.com.monktec.manager_money_api.repository.LancamentoRepository;
import br.com.monktec.manager_money_api.repository.PessoaRepository;
import br.com.monktec.manager_money_api.service.exception.PessoaInexistenteOuInativaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Lancamento> listarLancamentos(){
        return lancamentoRepository.findAll();
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
}
