package br.com.monktec.manager_money_api.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import br.com.monktec.manager_money_api.event.MoneyApiEvent;
import br.com.monktec.manager_money_api.event.listener.MoneyApiListner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.monktec.manager_money_api.model.Pessoa;
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
	public ResponseEntity<Pessoa> postPessoa(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response){
		Pessoa pessoaSalva = pessoaService.salvarPessoa(pessoa);
		
		publisher.publishEvent(new MoneyApiEvent(this, response, pessoaSalva.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
	}
}
