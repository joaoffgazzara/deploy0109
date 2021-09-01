package com.projetointegradorgrupo3.ProjetoReintegrar.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="tema")
public class Tema {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	@Size(min = 0, max = 100)
	private String descricao;
	
	@OneToMany (mappedBy = "tema", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties ("tema")
	private List<Usuario> postagem;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Usuario> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Usuario> postagem) {
		this.postagem = postagem;
	}
	
	
}
