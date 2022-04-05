package com.carlos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carlos.model.Estado;
import com.carlos.repository.EstadoDAO;

@Service
public class ServiceEstado implements IServiceEstado {

	@Autowired
	private EstadoDAO estadoDAO;
	
	@Override
	public List<Estado> listarEstados() {
		
		return (List<Estado>) estadoDAO.findAll();
	}

	@Override
	public void add(Estado estado) {
		
		estadoDAO.save(estado);
	}

	@Override
	public void delete(Estado estado) {
		
		estadoDAO.delete(estado);
	}

}
