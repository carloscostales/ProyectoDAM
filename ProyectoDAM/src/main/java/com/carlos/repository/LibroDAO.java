package com.carlos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.carlos.model.Libro;

@Repository
public interface LibroDAO extends CrudRepository<Libro, String> {

	@Query(value="SELECT * FROM libros WHERE autor_id = :id", nativeQuery = true)
	List<Libro> listarLibros(@Param("id") Integer id);
}
