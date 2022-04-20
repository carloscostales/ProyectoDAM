package com.carlos.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.carlos.model.Usuario;

@Repository
public interface UsuarioDAO extends CrudRepository<Usuario, String> {

	@Query(value="SELECT * FROM usuario WHERE nombre_usuario = :nombreUsuario", nativeQuery=true)
	Usuario buscarNombreUsuario(@Param("nombreUsuario") String nombreUsuario);
}
