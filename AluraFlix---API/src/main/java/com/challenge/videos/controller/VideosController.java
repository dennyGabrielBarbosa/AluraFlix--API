package com.challenge.videos.controller;

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

import com.challenge.videos.controller.dto.VideosDto;
import com.challenge.videos.controller.form.VideosAtualizacaoForm;
import com.challenge.videos.controller.form.VideosForm;
import com.challenge.videos.modelo.Videos;
import com.challenge.videos.repository.CategoriasRepository;
import com.challenge.videos.repository.VideosRepository;

@RestController
@RequestMapping(value = "videos")
public class VideosController {

	@Autowired
	private VideosRepository videosRepository;
	@Autowired
	private CategoriasRepository categoriaRepostiry;

	@GetMapping
	@ResponseBody
	public ResponseEntity<Stream<VideosDto>> listarVideos(@RequestParam(required = false, value = "search") String titulo,
			@RequestParam int pagina) {
		Pageable paginacao = PageRequest.of(pagina, 5);
		if (titulo == null) {
			Page<Videos> videos = videosRepository.findAll(paginacao);
			return ResponseEntity.ok().body(videos.stream().map(VideosDto::new));
		}
		Page<Videos> videoPorTitulo = videosRepository.findByTituloContains(titulo, paginacao);
		return ResponseEntity.ok().body(videoPorTitulo.stream().map(VideosDto::new));
	}

	@GetMapping("/{id}")
	public ResponseEntity<VideosDto> BuscaVideoPorId(@PathVariable(value = "id") Long id) {
		Optional<Videos> optional = videosRepository.findById(id);
		if (optional.isPresent()) {
			return ResponseEntity.ok(new VideosDto(optional.get()));
		}
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/free")
	@ResponseBody
	public ResponseEntity<Stream<Object>> ListaVideosSemAutenticacao(){
		Pageable paginacao = PageRequest.of(0, 5);
		Page<Videos> videos = videosRepository.findAll(paginacao);
		return ResponseEntity.ok().body(videos.stream().map(VideosDto::new));
	}

	@PostMapping
	public ResponseEntity<VideosDto> CadastraVideo(@Valid @RequestBody VideosForm form) {
		Videos video = form.transformaEmVideo(categoriaRepostiry);
		if (videosRepository.existsById(video.getId())) {
			return ResponseEntity.badRequest().build();
		}
		videosRepository.save(video);
		return ResponseEntity.ok(new VideosDto(video));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<VideosDto> AtualizaVideo(@PathVariable(value = "id") Long id,
			@RequestBody @Valid VideosAtualizacaoForm form) {
		Optional<Videos> optional = videosRepository.findById(id);
		if (optional.isPresent()) {
			Videos video = form.atualizar(id, videosRepository);
			return ResponseEntity.ok(new VideosDto(video));
		}
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> DeletaVideo(@PathVariable(value = "id") Long id) {
		if (videosRepository.existsById(id)) {
			videosRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.noContent().build();
	}
}