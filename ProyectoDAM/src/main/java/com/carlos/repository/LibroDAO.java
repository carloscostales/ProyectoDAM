package com.carlos.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.carlos.model.Libro;

@Repository
public interface LibroDAO extends JpaRepository<Libro, String> {

	@Query(value="SELECT * FROM libro WHERE autor_id = :id", nativeQuery = true)
	List<Libro> listarLibrosPorAutor(@Param("id") Integer id);

	@Query(value="SELECT * FROM libro WHERE autor_id = :id", nativeQuery = true)
	Page<Libro> listarLibrosPorAutor(@Param("id") Integer id, Pageable pageable);

	@Query(value="SELECT * FROM libro WHERE genero_codigo = :codigo", nativeQuery = true)
	List<Libro> listarLibrosPorGenero(@Param("codigo") String codigo);
	
	@Query(value="SELECT * FROM libro WHERE genero_codigo = :codigo", nativeQuery = true)
	Page<Libro> listarLibrosPorGenero(@Param("codigo") String codigo, Pageable pageable);

	@Query(value="SELECT * FROM libro WHERE genero_codigo = :codigo AND titulo like %:titulo%", nativeQuery = true)
	Page<Libro> listarLibrosPorGeneroBusqueda(@Param("codigo") String codigo, @Param("titulo") String titulo, Pageable pageable);
	
	@Query(value="SELECT * FROM libro ORDER BY ano DESC", nativeQuery = true)
	List<Libro> listarLibrosPorFechaDesc();

	@Query(value="SELECT * FROM libro ORDER BY created_at DESC", nativeQuery = true)
	List<Libro> listarLibrosCreatedAtDesc();

	@Query(value="SELECT l.* FROM libro_estado_usuario leu JOIN libro l ON leu.libro_isbn=l.isbn WHERE autor_id = :autor GROUP BY l.isbn ORDER BY count(l.isbn) desc LIMIT 1", nativeQuery = true)
    Libro libroDestacadoPorAutor(@Param("autor") Integer autor);
	
    @Query(value="SELECT l.* FROM libro_estado_usuario leu JOIN libro l ON leu.libro_isbn=l.isbn WHERE estado_id = :estado AND usuario_nombre_usuario = :nombreUsuario", nativeQuery = true)
	List<Libro> listarLibrosPorEstadoUsuario(@Param("nombreUsuario") String nombreUsuario, @Param("estado") Integer estado);

	@Query(value="SELECT l.* FROM libro_estado_usuario leu JOIN libro l ON leu.libro_isbn=l.isbn GROUP BY leu.libro_isbn ORDER BY count(libro_isbn) DESC", nativeQuery = true)
	List<Libro> listarLibrosMasSeguidos();

    @Query(value="DELETE FROM libro WHERE isbn = :isbn", nativeQuery=true)
    @Transactional
    @Modifying
    void borrarPorIsbn(@Param("isbn") String isbn);
}
