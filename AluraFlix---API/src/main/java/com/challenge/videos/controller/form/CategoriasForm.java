package com.challenge.videos.controller.form;

import javax.validation.constraints.NotEmpty;

import com.challenge.videos.modelo.Categorias;
import com.challenge.videos.repository.CategoriasRepository;

public class CategoriasForm {

	@NotEmpty(message = "O campo não pode estar vazio")
	private String titulo;
	@NotEmpty(message = "O campo é obrigatório")
	private String cor;

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public Categorias transformaEmCategorias(CategoriasRepository categoriasrepository) {
		Categorias categoria = new Categorias();
		
		categoria.setTitulo(titulo);
		categoria.setCor(cor);
		
		return categoria;
	}

}
