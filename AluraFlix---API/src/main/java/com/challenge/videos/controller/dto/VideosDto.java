package com.challenge.videos.controller.dto;

import java.util.List;

import com.challenge.videos.modelo.Videos;

public class VideosDto {

	private long id;
	private long categoriaId;
	private String titulo;
	private String descricao;
	private String url;

	public VideosDto(Videos video) {
		this.id = video.getId();
		this.titulo = video.getTitulo();
		this.descricao = video.getDescricao();
		this.url = video.getUrl();
		this.categoriaId = video.getCategoria().getId();
	}

	public VideosDto(List<Videos> listaDeVideos) {
	}

	public long getCategoriaId() {
		return categoriaId;
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getUrl() {
		return url;
	}

}
