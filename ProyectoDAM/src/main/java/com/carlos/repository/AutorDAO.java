package com.carlos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.carlos.model.Autor;

@Repository
public interface AutorDAO extends JpaRepository<Autor, Integer> {

	@Query(value="SELECT * FROM autor ORDER BY created_at DESC", nativeQuery = true)
	List<Autor> listarAutoresCreatedAtDesc();

	@Query(value="SELECT a.* FROM seguir s JOIN autor a ON s.autor_id=a.id GROUP BY a.id ORDER BY count(s.autor_id) DESC", nativeQuery = true)
    List<Autor> listarAutoresMasSeguidos();

	Page<Autor> findByNombreContaining(Pageable pageable, String nombre);

	@Query(value="SELECT a.* FROM libro_estado_usuario leu JOIN libro l ON leu.libro_isbn=l.isbn JOIN autor a ON l.autor_id=a.id WHERE leu.usuario_nombre_usuario = :nombreUsuario GROUP BY autor_id ORDER BY count(autor_id) DESC LIMIT 1", nativeQuery = true)
    Autor autorPreferidoPorUsuario(@Param("nombreUsuario") String nombreUsuario);
}
