package com.challenge.videos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.videos.modelo.Categorias;

public interface CategoriasRepository extends JpaRepository<Categorias, Long> {
	
}
