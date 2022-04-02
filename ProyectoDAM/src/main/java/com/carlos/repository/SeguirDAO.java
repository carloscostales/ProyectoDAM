package com.carlos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.carlos.model.Seguir;
import com.carlos.model.Usuario;

public interface SeguirDAO extends CrudRepository<Seguir, Integer> {
	
	List<Seguir> findByUsuario(Usuario usuario);
	
	@Query(value="SELECT * FROM artista WHERE id!=:id AND genero_nombre=:genero LIMIT 12", nativeQuery=true)
	List<Seguir> findArtistasGenero(@Param("id") Integer id, @Param("genero") String genero);
}
