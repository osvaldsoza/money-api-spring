package br.com.monktec.manager_money_api.controller;

import br.com.monktec.manager_money_api.event.MoneyApiEvent;
import br.com.monktec.manager_money_api.model.lancamento.Lancamento;
import br.com.monktec.manager_money_api.model.lancamento.LancamentoFilter;
import br.com.monktec.manager_money_api.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {

    @Autowired
    private LancamentoService lancamentoService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public Page<Lancamento> getLancamentos(LancamentoFilter lancamentoFilter, Pageable pageable) {
        return lancamentoService.listarLancamentosPorFiltros(lancamentoFilter , pageable);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Lancamento> getLancamentoPeloCodigo(@PathVariable Long codigo) {
        Lancamento lancamentoSalvo = lancamentoService.buscarLancamentoPeloCodigo(codigo);
        return lancamentoSalvo != null ? ResponseEntity.ok(lancamentoSalvo) : ResponseEntity.notFound().build();
    }

    @GetMapping("/dataVencimento/{dataVencimento}")
    public List<Lancamento> getLancamentosPorDataVencimento(@PathVariable Date dataVencimento){
        return lancamentoService.listarLancamentoPorDataVencimento(dataVencimento);
    }

    @PostMapping
    public ResponseEntity<Lancamento> postLancamento(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {
        Lancamento lancamentoSalvo = lancamentoService.salvarLancamento(lancamento);

        publisher.publishEvent(new MoneyApiEvent(this, response, lancamentoSalvo.getCodigo()));

        return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLancamento(@PathVariable Long codigo){
        lancamentoService.deletarLancamneto(codigo);
    }
}
