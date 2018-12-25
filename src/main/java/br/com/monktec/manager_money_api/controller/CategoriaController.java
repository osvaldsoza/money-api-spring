package br.com.monktec.manager_money_api.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import br.com.monktec.manager_money_api.event.MoneyApiEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.monktec.manager_money_api.model.Categoria;
import br.com.monktec.manager_money_api.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Categoria> getCategorias() {
		return categoriaService.listarCategorias();
	}

	@PostMapping
	public ResponseEntity<Categoria> postCategoria(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {
		Categoria categoriaSalva = categoriaService.salvarCategoria(categoria);

		publisher.publishEvent(new MoneyApiEvent(this, response, categoriaSalva.getCodigo()));

		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Categoria> getCategoriaPeloCodigo(@PathVariable Long codigo) {
		Categoria categoriaEncontrada = categoriaService.buscarPeloCodigo(codigo);
		return categoriaEncontrada != null ? ResponseEntity.ok(categoriaEncontrada) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCategoria(@PathVariable Long codigo){
	    categoriaService.deletarCategoria(codigo);
    }
}
