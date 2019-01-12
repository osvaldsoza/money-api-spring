package br.com.monktec.manager_money_api.model.pessoa;

import br.com.monktec.manager_money_api.model.endereco.Endereco;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "pessoa")
public class Pessoa {

	private Long codigo;
	private String nome;
	private Boolean ativo;
	private Endereco endereco;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	@NotNull
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@NotNull
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	@Embedded
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@JsonIgnore
    @Transient
	public Boolean isInativa(){
	    return !ativo;
    }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Pessoa)) return false;
		Pessoa pessoa = (Pessoa) o;
		return Objects.equals(codigo, pessoa.codigo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}
}
