package com.carlos.service;

import java.util.List;

import com.carlos.model.Autor;

public interface IServiceAutor {

	public void add(Autor autor);
	
	public void update(Autor autor);
	
	public void delete(Autor autor);
	
	public List<Autor> listarAutores();

	public List<Autor> listarAutoresCreatedAtDesc();

	public List<Autor> listarAutoresMasSeguidos();
}
