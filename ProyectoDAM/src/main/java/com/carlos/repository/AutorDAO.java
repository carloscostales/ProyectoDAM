package com.carlos.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.carlos.model.Autor;

@Repository
public interface AutorDAO extends CrudRepository<Autor, Integer> {

	@Query(value="SELECT * FROM autor ORDER BY created_at DESC", nativeQuery = true)
	List<Autor> listarAutoresCreatedAtDesc();

	@Query(value="SELECT a.* FROM seguir s JOIN autor a ON s.autor_id=a.id GROUP BY a.id ORDER BY count(s.autor_id) DESC", nativeQuery = true)
    List<Autor> listarAutoresMasSeguidos();
}
