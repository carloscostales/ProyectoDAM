package com.carlos.service;

import java.util.List;

import com.carlos.model.Genero;

public interface IServiceGenero {

	public void add(Genero genero);
	
	public void borrarPorCodigo(Genero genero);
	
	public List<Genero> todosGeneros();
}
