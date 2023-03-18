package com.challenge.videos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenge.videos.modelo.Categorias;
import com.challenge.videos.modelo.Videos;

@Repository
public interface VideosRepository extends JpaRepository<Videos, Long> {

	Page<Videos> findByTituloContains(String titulo,Pageable paginacao);
	List<Videos> findAllVideosByCategoria(Optional<Categorias> optional);
}