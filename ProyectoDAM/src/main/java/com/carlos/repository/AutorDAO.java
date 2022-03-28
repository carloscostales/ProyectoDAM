package com.carlos.repository;

import org.springframework.data.repository.CrudRepository;

import com.carlos.model.Autor;

public interface AutorDAO extends CrudRepository<Autor, Integer> {

}
