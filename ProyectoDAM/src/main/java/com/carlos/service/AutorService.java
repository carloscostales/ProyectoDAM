package com.carlos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carlos.model.Autor;
import com.carlos.repository.AutorDAO;

@Service
public class AutorService implements IAutorService {
	
	@Autowired
	private AutorDAO autorDAO;
	

	@Override
	public List<Autor> listarAutores() {

		return (List<Autor>) autorDAO.findAll();
	}

	@Override
	public void add(Autor autor) {
		autorDAO.save(autor);
		
	}

	@Override
	public void delete(Autor autor) {
		autorDAO.delete(autor);
		
	}
}
