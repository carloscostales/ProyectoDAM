package com.carlos.service;

import java.util.List;

import com.carlos.model.Libro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IServiceLibro {
	
	public void add(Libro libro);

	public void update(Libro libro);
	
	public void delete(Libro libro);
	
	public List<Libro> listarLibros();
	
	public List<Libro> listarLibrosAutor(Integer id);

	public Page<Libro> listarLibrosAutor(Integer id, Pageable pageable);

	public List<Libro> listarLibrosPorGenero(String codigo);
	
	public Page<Libro> listarLibrosPorGenero(String codigo, Pageable pageable);
		
	public Page<Libro> listarLibrosPorGeneroBusqueda(String codigo, String titulo, Pageable pageable);
	
	public List<Libro> listarLibrosFechaDesc();

	public List<Libro> listarLibrosCreatedAtDesc();

	public Libro libroDestacadoPorAutor(Integer autorId);

	public List<Libro> listarLibrosPorEstadoUsuario(String nombreUsuario, Integer estado);
}
