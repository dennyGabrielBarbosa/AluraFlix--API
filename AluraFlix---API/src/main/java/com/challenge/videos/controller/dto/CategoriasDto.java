package com.challenge.videos.controller.dto;

import com.challenge.videos.modelo.Categorias;

public class CategoriasDto {

	private long id;
	private String titulo;
	private String cor;

	public long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getCor() {
		return cor;
	}

	public CategoriasDto(Categorias categoria) {
		this.id = categoria.getId();
		this.titulo = categoria.getTitulo();
		this.cor = categoria.getCor();
	}

}
