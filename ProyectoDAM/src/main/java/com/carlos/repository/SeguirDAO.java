package com.carlos.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.carlos.model.Seguir;
import com.carlos.model.Usuario;

public interface SeguirDAO extends CrudRepository<Seguir, Integer> {
	
	List<Seguir> findByUsuario(Usuario usuario);
	
	@Query(value="SELECT * FROM seguir WHERE autor_id = :id AND usuario_nombre_usuario = :nombreUsuario", nativeQuery=true)
	Seguir buscarId(@Param("id") Integer id, @Param("nombreUsuario") String nombreUsuario);
	
	@Query(value="SELECT count(id) FROM seguir WHERE autor_id = :id AND usuario_nombre_usuario = :nombreUsuario", nativeQuery=true)
	Integer comprobarSeguir(@Param("id") Integer id, @Param("nombreUsuario") String nombreUsuario);
	
	@Query(value="SELECT count(id) FROM seguir WHERE autor_id = :id", nativeQuery=true)
	Integer numeroSeguidores(@Param("id") Integer id);
	
	@Query(value="DELETE FROM seguir WHERE id = :id", nativeQuery=true)
	@Transactional
	@Modifying
	void borrarSeguimiento(@Param("id") Integer id);
}
