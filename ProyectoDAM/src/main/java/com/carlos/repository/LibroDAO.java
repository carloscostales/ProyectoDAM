package com.carlos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.carlos.model.Libro;

public interface LibroDAO extends CrudRepository<Libro, String> {

	@Query(value="SELECT * FROM libro WHERE autor_id = :id", nativeQuery = true)
	List<Libro> listarLibros(@Param("id") Integer id);
}
