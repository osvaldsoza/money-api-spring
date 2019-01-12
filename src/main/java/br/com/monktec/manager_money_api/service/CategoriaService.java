package br.com.monktec.manager_money_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.monktec.manager_money_api.model.categoria.Categoria;
import br.com.monktec.manager_money_api.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<Categoria> listarCategorias(){
		return categoriaRepository.findAll();
	}

	public Categoria salvarCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	public Categoria buscarPeloCodigo(Long codgio) {
		return categoriaRepository.findOne(codgio);
	}

	public void deletarCategoria(Long codigo){ categoriaRepository.delete(codigo); }
}
