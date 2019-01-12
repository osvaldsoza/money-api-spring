package br.com.monktec.manager_money_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.monktec.manager_money_api.model.categoria.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
