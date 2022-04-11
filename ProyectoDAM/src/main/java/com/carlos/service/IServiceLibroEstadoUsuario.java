package com.carlos.service;

import com.carlos.model.Libro;
import com.carlos.model.LibroEstadoUsuario;
import com.carlos.model.Usuario;

public interface IServiceLibroEstadoUsuario {

	public void add(LibroEstadoUsuario libroEstado);
	
	public void update(LibroEstadoUsuario libroEstado);
	
	public void delete(LibroEstadoUsuario libroEstado);
	
	public void deleteById(Integer id);
	
	public void deleteByLibro(String isbn);
	
	public LibroEstadoUsuario estadoDeLibro(Libro libro, Usuario usuario);
}
