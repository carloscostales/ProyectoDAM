package com.carlos.service;

import java.util.List;

import com.carlos.model.Libro;

public interface IServiceLibro {
	
	public void add(Libro libro);

	public void update(Libro libro);
	
	public void delete(Libro libro);
	
	public List<Libro> listarLibros();
	
	public List<Libro> listarLibrosAutor(Integer id);
	
	public List<Libro> listarLibrosPorGenero(String codigo);
}
