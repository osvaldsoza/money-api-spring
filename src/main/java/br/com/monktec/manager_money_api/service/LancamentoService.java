package br.com.monktec.manager_money_api.service;

import br.com.monktec.manager_money_api.model.Lancamento;
import br.com.monktec.manager_money_api.repository.LancamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    public List<Lancamento> listarLancamentos(){
        return lancamentoRepository.findAll();
    }

    public Lancamento buscarLancamentoPeloCodigo(Long codigo){
        return lancamentoRepository.findOne(codigo);
    }

    public Lancamento salvarLancamento(Lancamento lancamento){
        return lancamentoRepository.save(lancamento);
    }
}
