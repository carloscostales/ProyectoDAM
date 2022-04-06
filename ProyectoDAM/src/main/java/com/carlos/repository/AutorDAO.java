package com.carlos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.carlos.model.Autor;

@Repository
public interface AutorDAO extends CrudRepository<Autor, Integer> {

}
