package com.challenge.videos.controller.form;

import javax.validation.constraints.NotEmpty;

import com.challenge.videos.modelo.Categorias;
import com.challenge.videos.repository.CategoriasRepository;

public class CategoriasAtualizacaoForm {

	@NotEmpty(message = "O campo é obrigatório")
	private String titulo;
	@NotEmpty(message = "O campo é obrigatório")
	private String cor;

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public Categorias atualizar(Long id, CategoriasRepository categoriasRepository) {
		Categorias categoria = categoriasRepository.getReferenceById(id);
		
		categoria.setTitulo(this.titulo);
		categoria.setCor(this.cor);
		
		return null;
	}

}
