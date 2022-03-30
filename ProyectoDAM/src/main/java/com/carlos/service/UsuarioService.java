package com.carlos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carlos.model.Usuario;
import com.carlos.repository.UsuarioDAO;

@Service
public class UsuarioService implements IUsuarioService {

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Override
	public void add(Usuario usuario) {
		
		usuarioDAO.save(usuario);
	}

	@Override
	public void delete(Usuario usuario) {
		
		usuarioDAO.delete(usuario);
	}
	
	@Override
	public void deleteById(String nombreUsuario) {
		
		usuarioDAO.deleteById(nombreUsuario);
	}

	@Override
	public List<Usuario> todosUsuarios() {
		
		return (List<Usuario>) usuarioDAO.findAll();
	}
}
