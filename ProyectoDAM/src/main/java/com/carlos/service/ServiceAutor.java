package com.carlos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.carlos.model.Autor;
import com.carlos.repository.AutorDAO;

@Service
public class ServiceAutor implements IServiceAutor {
	
	@Autowired
	private AutorDAO autorDAO;

	@Override
	public void add(Autor autor) {
		autorDAO.save(autor);
	}
	
	@Override
	public void update(Autor autor) {
		if(autorDAO.existsById(autor.getId()))
			autorDAO.save(autor);
	}	

	@Override
	public void delete(Autor autor) {
		autorDAO.delete(autor);
	}

	@Override
	public List<Autor> listarAutores() {
		return (List<Autor>) autorDAO.findAll();
	}

	@Override
	public Page<Autor> listarAutores(Pageable pageable) {
		return (Page<Autor>) autorDAO.findAll(pageable);
	}

	@Override
	public List<Autor> listarAutoresCreatedAtDesc() {
		return (List<Autor>) autorDAO.listarAutoresCreatedAtDesc();
	}

	@Override
	public List<Autor> listarAutoresMasSeguidos() {
		return (List<Autor>) autorDAO.listarAutoresMasSeguidos();
	}

	@Override
	public Page<Autor> buscarPorNombre(Pageable pageable, String nombre) {
		return autorDAO.findByNombreStartsWith(pageable, nombre);
	}
}
