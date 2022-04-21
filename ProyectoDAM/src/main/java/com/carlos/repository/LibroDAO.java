package com.carlos.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.carlos.model.Libro;

@Repository
public interface LibroDAO extends CrudRepository<Libro, String> {

	@Query(value="SELECT * FROM libro WHERE autor_id = :id", nativeQuery = true)
	List<Libro> listarLibros(@Param("id") Integer id);
	
	@Query(value="SELECT * FROM libro WHERE genero_codigo = :codigo", nativeQuery = true)
	List<Libro> listarLibrosPorGenero(@Param("codigo") String codigo);
	
    @Query(value="DELETE FROM libro WHERE isbn = :isbn", nativeQuery=true)
    @Transactional
    @Modifying
    void borrarPorIsbn(@Param("isbn") String isbn);
}
