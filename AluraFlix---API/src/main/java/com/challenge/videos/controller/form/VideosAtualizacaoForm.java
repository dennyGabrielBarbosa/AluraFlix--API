package com.challenge.videos.controller.form;

import javax.validation.constraints.NotEmpty;

import com.challenge.videos.modelo.Videos;
import com.challenge.videos.repository.VideosRepository;

public class VideosAtualizacaoForm {

	@NotEmpty(message = "O campo é obrigatório")
	private String titulo;

	@NotEmpty(message = "O campo é obrigatório")
	private String descricao;

	@NotEmpty(message = "O campo é obrigatório")
	private String url;

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Videos atualizar(Long id, VideosRepository videosRepository) {
		Videos video = videosRepository.getReferenceById(id);

		video.setTitulo(this.titulo);
		video.setDescricao(this.descricao);
		video.setUrl(this.url);

		return video;
	}
}