package com.carlos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carlos.model.Libro;
import com.carlos.model.LibroEstadoUsuario;
import com.carlos.model.Usuario;
import com.carlos.repository.LibroEstadoUsuarioDAO;

@Service
public class ServiceLibroEstadoUsuario implements IServiceLibroEstadoUsuario {

	@Autowired
	private LibroEstadoUsuarioDAO libroEstadoUsuarioDAO;
	
	@Override
	public void add(LibroEstadoUsuario libroEstado) {
		libroEstadoUsuarioDAO.save(libroEstado);
	}

	@Override
	public void update(LibroEstadoUsuario libroEstado) {
		if(libroEstadoUsuarioDAO.existsById(libroEstado.getId()))
			libroEstadoUsuarioDAO.save(libroEstado);
	}
	
	@Override
	public void delete(LibroEstadoUsuario libroEstado) {
		libroEstadoUsuarioDAO.delete(libroEstado);
	}

	@Override
	public void deleteById(Integer id) {
		libroEstadoUsuarioDAO.borrarPorId(id);
	}
	
	@Override
	public void deleteByLibro(String isbn) {
		libroEstadoUsuarioDAO.borrarPorIsbn(isbn);
	}
	
	@Override
	public LibroEstadoUsuario estadoDeLibro(Libro libro, Usuario usuario) {
		
		return libroEstadoUsuarioDAO.estadoDeLibro(libro.getIsbn(), usuario.getNombreUsuario());
	}
}
