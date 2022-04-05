package com.carlos.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.carlos.model.LibroEstadoUsuario;

@Repository
public interface LibroEstadoUsuarioDAO extends CrudRepository<LibroEstadoUsuario, Integer> {

	@Query(value="SELECT * FROM libro_estado_usuario where libro_isbn = :isbn and usuario_nombre_usuario = :nombreUsuario", nativeQuery = true)
	LibroEstadoUsuario estadoDeLibro(@Param("isbn") String isbn, @Param("nombreUsuario") String nombreUsuario);
	
    @Query(value="DELETE FROM libro_estado_usuario  WHERE id = :id", nativeQuery=true)
    @Transactional
    @Modifying
    void borrarPorId(@Param("id") Integer id);
}
