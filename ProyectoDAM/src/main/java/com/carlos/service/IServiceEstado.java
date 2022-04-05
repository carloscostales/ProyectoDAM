package com.carlos.service;

import java.util.List;

import com.carlos.model.Estado;

public interface IServiceEstado {

	public List<Estado> listarEstados();
	
	public void add(Estado estado);
	
	public void delete(Estado estado);
}
