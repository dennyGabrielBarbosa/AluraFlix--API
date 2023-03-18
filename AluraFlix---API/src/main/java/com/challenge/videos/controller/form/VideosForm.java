package com.challenge.videos.controller.form;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.challenge.videos.modelo.Categorias;
import com.challenge.videos.modelo.Videos;
import com.challenge.videos.repository.CategoriasRepository;

public class VideosForm {

	@NotEmpty(message = "O campo é obrigatório")
	@Length(max = 30)
	private String titulo;

	@NotEmpty(message = "O campo é obrigatório")
	private String descricao;

	@NotEmpty(message = "O campo é obrigatório")
	private String url;

	private Long categoriaId = (long) 1;

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(long categoriaId) {
		this.categoriaId = categoriaId;
	}

	public Videos transformaEmVideo(CategoriasRepository categoriaRepostory) {
		Optional<Categorias> optional = categoriaRepostory.findById(categoriaId);
		optional.isPresent();
		Categorias categoria = optional.orElseThrow();
		Videos video = new Videos(titulo, descricao, url, categoria);
		return video;
	}

}
