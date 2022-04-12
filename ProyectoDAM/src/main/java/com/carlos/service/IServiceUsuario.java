package com.carlos.service;

import java.util.List;

import com.carlos.model.Usuario;

public interface IServiceUsuario {

	public void add(Usuario usuario);
	
	public void update(Usuario usuario);
	
	public void delete(Usuario usuario);
	
	public void deleteById(String nombreUsuario);
	
	public List<Usuario> todosUsuarios();
	
	public Usuario buscarNombreUsuario(String nombreUsuario);
}
