package com.carlos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.carlos.model.Rol;

@Repository
public interface RolDAO extends CrudRepository<Rol, String> {

}
