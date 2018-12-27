package br.com.monktec.manager_money_api.controller;

import br.com.monktec.manager_money_api.event.MoneyApiEvent;
import br.com.monktec.manager_money_api.model.Lancamento;
import br.com.monktec.manager_money_api.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {

    @Autowired
    private LancamentoService lancamentoService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<Lancamento> getLancamentos() {
        return lancamentoService.listarLancamentos();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Lancamento> getLancamentoPeloCodigo(@PathVariable Long codigo) {
        Lancamento lancamentoSalvo = lancamentoService.buscarLancamentoPeloCodigo(codigo);

        return lancamentoSalvo != null ? ResponseEntity.ok(lancamentoSalvo) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Lancamento> postLancamento(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {
        Lancamento lancamentoSalvo = lancamentoService.salvarLancamento(lancamento);

        publisher.publishEvent(new MoneyApiEvent(this, response, lancamentoSalvo.getCodigo()));

        return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
    }
}
