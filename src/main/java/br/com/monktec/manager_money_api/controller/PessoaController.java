package br.com.monktec.manager_money_api.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.monktec.manager_money_api.event.MoneyApiEvent;
import br.com.monktec.manager_money_api.model.pessoa.Pessoa;
import br.com.monktec.manager_money_api.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<Pessoa> getPessoas() {
        return pessoaService.listarPessoas();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Pessoa> getPessoaPeloCodigo(@PathVariable Long codigo) {
        Pessoa pessoaEncontrada = pessoaService.buscarPeloCodigo(codigo);
        return pessoaEncontrada != null ? ResponseEntity.ok(pessoaEncontrada) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Pessoa> postPessoa(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
        Pessoa pessoaSalva = pessoaService.salvarPessoa(pessoa);

        publisher.publishEvent(new MoneyApiEvent(this, response, pessoaSalva.getCodigo()));

        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePessoa(@PathVariable Long codigo) {
        pessoaService.deletaPessoa(codigo);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Pessoa> putPessoa(@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa){
       Pessoa pessoaSalva =  pessoaService.atualizarPessoa(codigo, pessoa);

        return ResponseEntity.ok(pessoaSalva);
    }

    @PutMapping("/{codigo}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo ){
        pessoaService.atualizarPropriedadeAtivo(codigo, ativo);
    }
}
