package com.carlos.service;

import java.util.List;

import com.carlos.model.Autor;

public interface IServiceAutor {

	public List<Autor> listarAutores();
	
	public void add(Autor autor);
	
	public void delete(Autor autor);
	
}
