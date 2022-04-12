package com.carlos.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.carlos.model.Usuario;

@Service
public class ServiceUltimoLogin implements ApplicationListener<AuthenticationSuccessEvent> {
	
	@Autowired
	private IServiceUsuario usuarioService;

	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
		String userName = ((UserDetails) event.getAuthentication().getPrincipal()).getUsername();
		Usuario user = this.usuarioService.buscarNombreUsuario(userName);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		user.setUltimoLogin(sdf.format(new Date()));
		usuarioService.update(user);
	}
}