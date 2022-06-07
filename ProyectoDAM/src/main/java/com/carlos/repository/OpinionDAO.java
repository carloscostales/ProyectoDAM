package com.carlos.repository;

import java.util.List;

import com.carlos.model.Opinion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OpinionDAO extends JpaRepository<Opinion, Integer> {
    
	@Query(value="SELECT * FROM opinion WHERE libro_isbn = :isbn", nativeQuery = true)
	List<Opinion> listarLibrosPorGenero(@Param("isbn") String codigo);
}
