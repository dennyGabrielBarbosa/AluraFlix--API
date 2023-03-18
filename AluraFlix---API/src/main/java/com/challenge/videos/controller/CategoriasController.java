package com.challenge.videos.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.videos.controller.dto.CategoriasDto;
import com.challenge.videos.controller.form.CategoriasAtualizacaoForm;
import com.challenge.videos.controller.form.CategoriasForm;
import com.challenge.videos.modelo.Categorias;
import com.challenge.videos.modelo.Videos;
import com.challenge.videos.repository.CategoriasRepository;
import com.challenge.videos.repository.VideosRepository;

@RestController
@RequestMapping(value = "categorias")
public class CategoriasController {

	@Autowired
	private CategoriasRepository categoriasRepository;
	@Autowired
	private VideosRepository videosRepository;

	@GetMapping
	@ResponseBody
	public ResponseEntity<Stream<CategoriasDto>> listarCategorias(@RequestParam int pagina) {
		Pageable paginacao = PageRequest.of(pagina, 5);
		Page<Categorias> categorias = categoriasRepository.findAll(paginacao);
		return ResponseEntity.ok().body(categorias.stream().map(CategoriasDto::new));
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoriasDto> BuscaCategoriaPorId(@PathVariable(value = "id") Long id) {
		Optional<Categorias> optional = categoriasRepository.findById(id);
		if (optional.isPresent()) {
			return ResponseEntity.ok().body(new CategoriasDto(optional.get()));
		}
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}/videos")
	public ResponseEntity<?> BuscaVideosPorCategoriaId(@PathVariable(value = "id") Long id) {
		Optional<Categorias> optional = categoriasRepository.findById(id);
		if (optional.isPresent()) {
			List<Videos> video = videosRepository.findAllVideosByCategoria(optional);
			return ResponseEntity.ok().body(video);
		}
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<CategoriasDto> CadastraCategoria(@Valid @RequestBody CategoriasForm form) {
		Categorias categoria = form.transformaEmCategorias(categoriasRepository);
		if (categoriasRepository.existsById(categoria.getId())) {
			return ResponseEntity.badRequest().build();
		}
		categoriasRepository.save(categoria);
		return ResponseEntity.ok().body(new CategoriasDto(categoria));
	}

	@PutMapping
	public ResponseEntity<CategoriasDto> AtualizaCategorias(@PathVariable(value = "id") Long id,
			@RequestBody @Valid CategoriasAtualizacaoForm form) {
		Optional<Categorias> optional = categoriasRepository.findById(id);
		if (optional.isPresent()) {
			Categorias categoria = form.atualizar(id, categoriasRepository);
			return ResponseEntity.ok(new CategoriasDto(categoria));
		}
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> DeletaCategoria(@PathVariable(value = "id") Long id) {
		if (id == 1) {
			return ResponseEntity.badRequest().body("Esta categoria não pode ser deletada");
		}
		if (categoriasRepository.existsById(id)) {
			categoriasRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.noContent().build();
	}
}
