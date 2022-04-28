package com.carlos.service;

import java.util.List;

import com.carlos.model.Autor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IServiceAutor {

	public void add(Autor autor);
	
	public void update(Autor autor);
	
	public void delete(Autor autor);
	
	public List<Autor> listarAutores();

	public Page<Autor> listarAutores(Pageable pageable);

	public List<Autor> listarAutoresCreatedAtDesc();

	public List<Autor> listarAutoresMasSeguidos();
}
