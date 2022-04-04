package com.carlos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carlos.model.Seguir;
import com.carlos.model.Usuario;
import com.carlos.repository.SeguirDAO;

@Service
public class ServiceSeguir implements IServiceSeguir {
	
	@Autowired
	private SeguirDAO seguirDAO;

	@Override
	public Seguir buscarSeguir(Integer id, String nombreUsuario) {
		
		return seguirDAO.buscarId(id, nombreUsuario);
	}
	
	@Override
	public List<Seguir> buscarAutoresDeUsuario(Usuario usuario) {
		
		return seguirDAO.findByUsuario(usuario);
	}
	
	@Override
	public Integer comprobarSeguir(Integer id, String nombreUsuario) {
		
		return seguirDAO.comprobarSeguir(id, nombreUsuario);
	}

	@Override
	public Integer numeroSeguidores(Integer id) {
		
		return seguirDAO.numeroSeguidores(id);
	}

	@Override
	public void add(Seguir seguir) {
		seguirDAO.save(seguir);
	}

	@Override
	public void delete(Seguir seguir) {
		seguirDAO.borrarSeguimiento(seguir.getId());
		System.out.println(seguir.getId());
	}
}
