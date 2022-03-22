package com.carlos.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.carlos.model.Rol;
import com.carlos.model.Usuario;
import com.carlos.service.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	public UsuarioService usuarioService;
	
	@GetMapping("/{usuario}")
	public ModelAndView usuario(@PathVariable Usuario usuario) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("usuarios/usuario");
		mav.addObject("usuario", usuario);
		
		return mav;
	}

	@GetMapping("/listaUsuarios")
	public ModelAndView listaUsuarios(Authentication auth) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("usuarios/listaUsuarios");
		List<Usuario> lista = (List<Usuario>) usuarioService.findAll();
		
		mav.addObject("usuarios", lista);
		
		if(auth != null) {
			Usuario usuario = (Usuario) auth.getPrincipal();
			mav.addObject("usuario", usuario);
		}
		
		return mav;
	}
	
	@GetMapping("/registro")
	public ModelAndView registro() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("usuarios/registro");
		
		Usuario usuario = new Usuario();
		mav.addObject("usuario", usuario);
		
		return mav;
	}
	
	@PostMapping("/addUsuario")
	public ModelAndView addUser(@Valid @ModelAttribute Usuario usuario, BindingResult bindingResult) {
		
		ModelAndView mav = new ModelAndView();
		
		if(bindingResult.hasErrors()) {
			mav.setViewName("usuarios/registro");
			
			return mav;
		}
		
		Rol rol = new Rol();
		usuario.setRol(rol);
		
		usuarioService.add(usuario);
		
		mav.setViewName("index");
		
		return mav;
	}
}
