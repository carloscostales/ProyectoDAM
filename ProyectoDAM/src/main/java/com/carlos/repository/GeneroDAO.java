package com.carlos.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.carlos.model.Genero;

@Repository
public interface GeneroDAO extends CrudRepository<Genero, String>  {
	
    @Query(value="delete FROM genero where codigo = :cod", nativeQuery=true)
    @Transactional
    @Modifying
    void borrarPorCodigo(@Param("cod") String cod);
}
