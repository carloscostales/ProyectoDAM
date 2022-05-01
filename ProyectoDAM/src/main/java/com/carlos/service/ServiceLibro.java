package com.carlos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.carlos.model.Libro;
import com.carlos.repository.LibroDAO;

@Service
public class ServiceLibro implements IServiceLibro {
	
	@Autowired
	private LibroDAO libroDAO;

	@Override
	public void add(Libro libro) {
		libroDAO.save(libro);
	}

	@Override
	public void update(Libro libro) {
		if(libroDAO.existsById(libro.getIsbn()))
			libroDAO.save(libro);
	}
	
	@Override
	public void delete(Libro libro) {
		libroDAO.borrarPorIsbn(libro.getIsbn());
	}

	@Override
	public List<Libro> listarLibros() {
		return (List<Libro>) libroDAO.findAll();
	}

	@Override
	public List<Libro> listarLibrosAutor(Integer id) {
		return (List<Libro>) libroDAO.listarLibros(id);
	}

	@Override
	public List<Libro> listarLibrosPorGenero(String codigo) {
		return (List<Libro>) libroDAO.listarLibrosPorGenero(codigo);
	}

	@Override
	public Page<Libro> listarLibrosPorGenero(String codigo, Pageable pageable) {
		return (Page<Libro>) libroDAO.listarLibrosPorGenero(codigo, pageable);
	}

	@Override
	public List<Libro> listarLibrosFechaDesc() {
		return (List<Libro>) libroDAO.listarLibrosPorFechaDesc();
	}

	@Override
	public List<Libro> listarLibrosCreatedAtDesc() {
		return (List<Libro>) libroDAO.listarLibrosCreatedAtDesc();
	}

	@Override
	public Libro libroDestacadoPorAutor(Integer autorId) {
		return libroDAO.libroDestacadoPorAutor(autorId);
	}

	@Override
	public List<Libro> listarLibrosPorEstadoUsuario(String nombreUsuario, Integer estado) {
		return (List<Libro>) libroDAO.listarLibrosPorEstadoUsuario(nombreUsuario, estado);
	}
}
