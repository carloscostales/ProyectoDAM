package com.carlos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.carlos.model.Usuario;
import com.carlos.service.IServiceAutor;
import com.carlos.service.IServiceLibro;

@Controller
public class RutasGenericas {
		
	@Autowired
	private IServiceLibro libroService;

	@Autowired
	private IServiceAutor autorService;
	
	
	@GetMapping("/")
	public ModelAndView index(Authentication auth) {
		ModelAndView mav = new ModelAndView();
		if(auth != null) {
			Usuario usuario = (Usuario) auth.getPrincipal();
			mav.addObject("usuario", usuario);
		}

		mav.addObject("librosSeguidos", libroService.listarLibrosMasSeguidos());
		mav.addObject("autoresSeguidos", autorService.listarAutoresMasSeguidos());
		mav.addObject("autoresRecientes", autorService.listarAutoresCreatedAtDesc());
		mav.addObject("librosRecientes", libroService.listarLibrosCreatedAtDesc());

		mav.setViewName("index");
		return mav;
	}

	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("login");
		return mav;
	}
	
	@GetMapping("/login-error")
	public ModelAndView loginError() {
		ModelAndView mav = new ModelAndView();
		
		boolean loginError = true;
		mav.addObject("loginError", loginError);
		
		mav.setViewName("login");
		return mav;
	}
}
