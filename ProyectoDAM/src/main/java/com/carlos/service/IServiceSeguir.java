package com.carlos.service;

import java.util.List;

import com.carlos.model.Seguir;
import com.carlos.model.Usuario;

public interface IServiceSeguir {

	public List<Seguir> buscarAutoresDeUsuario(Usuario usuario);
	
	public void add(Seguir seguir);
	
	public void delete(Seguir seguir);
}
