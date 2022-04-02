package com.carlos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carlos.model.Genero;
import com.carlos.repository.GeneroDAO;

@Service
public class ServiceGenero implements IServiceGenero {
	
	@Autowired
	private GeneroDAO generoDAO; 

	@Override
	public void add(Genero genero) {
		
		generoDAO.save(genero);
	}

	@Override
	public void delete(Genero genero) {
		
		generoDAO.delete(genero);
	}

	@Override
	public void deleteById(String codGenero) {
		
		generoDAO.deleteById(codGenero);
	}

	@Override
	public List<Genero> todosGeneros() {
		return  (List<Genero>) generoDAO.findAll();
	}

	
}
