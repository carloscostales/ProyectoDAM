package com.carlos.repository;

import org.springframework.data.repository.CrudRepository;

import com.carlos.model.Libro;

public interface LibroDAO extends CrudRepository<Libro, String> {

}
