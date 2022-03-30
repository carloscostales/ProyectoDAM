package com.carlos.service;

import java.util.List;

import com.carlos.model.Libro;

public interface ILibroService {
	
	public List<Libro> listarLibros();
		
	public void add(Libro libro);
	
	public void delete(Libro libro);
}