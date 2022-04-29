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
	
	@Query(value="SELECT * FROM libro ORDER BY ano DESC", nativeQuery = true)
	List<Libro> listarLibrosPorFechaDesc();

	@Query(value="SELECT * FROM libro ORDER BY created_at DESC", nativeQuery = true)
	List<Libro> listarLibrosCreatedAtDesc();

	@Query(value="SELECT l.* FROM libro_estado_usuario leu JOIN libro l ON leu.libro_isbn=l.isbn WHERE autor_id = :autor GROUP BY l.isbn ORDER BY count(l.isbn) desc LIMIT 1", nativeQuery = true)
    Libro libroDestacadoPorAutor(@Param("autor") Integer autor);
	
    @Query(value="SELECT l.* FROM libro_estado_usuario leu JOIN libro l ON leu.libro_isbn=l.isbn WHERE estado_id = :estado AND usuario_nombre_usuario = :nombreUsuario", nativeQuery = true)
	List<Libro> listarLibrosLeidosUsuario(@Param("nombreUsuario") String nombreUsuario, @Param("estado") Integer estado);

    @Query(value="DELETE FROM libro WHERE isbn = :isbn", nativeQuery=true)
    @Transactional
    @Modifying
    void borrarPorIsbn(@Param("isbn") String isbn);
}
