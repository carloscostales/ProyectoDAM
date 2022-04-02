package com.carlos.service;

import java.util.List;

import com.carlos.model.Genero;

public interface IServiceGenero {

	public void add(Genero genero);
	
	public void delete(Genero genero);
	
	public void deleteById(String codGenero);
	
	public List<Genero> todosGeneros();
}
