package com.carlos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.carlos.model.Estado;

@Repository
public interface EstadoDAO extends CrudRepository<Estado, Integer> {

}
