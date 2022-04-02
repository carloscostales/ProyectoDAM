package com.carlos.service;

import java.util.List;

import com.carlos.model.Seguir;
import com.carlos.model.Usuario;

public interface IServiceSeguir {
	
	public Seguir buscarSeguir(Integer id, String nombreUsuario);

	public List<Seguir> buscarAutoresDeUsuario(Usuario usuario);
	
	public Integer comprobarSeguir(Integer id, String nombreUsuario);
	
	public void add(Seguir seguir);
	
	public void delete(Seguir seguir);
}
