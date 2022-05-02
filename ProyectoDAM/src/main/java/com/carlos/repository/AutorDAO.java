package com.carlos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.carlos.model.Autor;

@Repository
public interface AutorDAO extends JpaRepository<Autor, Integer> {

	@Query(value="SELECT * FROM autor ORDER BY created_at DESC", nativeQuery = true)
	List<Autor> listarAutoresCreatedAtDesc();

	@Query(value="SELECT a.* FROM seguir s JOIN autor a ON s.autor_id=a.id GROUP BY a.id ORDER BY count(s.autor_id) DESC", nativeQuery = true)
    List<Autor> listarAutoresMasSeguidos();

	// Busca artistas que empiezen por el string dado. Hecho para paginar la búsqueda
	Page<Autor> findByNombreContains(Pageable pageable, String nombre);
}
