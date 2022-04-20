package com.carlos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.carlos.model.Genero;
import com.carlos.model.Usuario;
import com.carlos.service.IServiceGenero;

@Controller
@RequestMapping("/genero")
public class GeneroController {

	@Autowired
	private IServiceGenero generoService;
	
	
	@GetMapping("/generos")
	public ModelAndView generos(Authentication auth) {
		ModelAndView mav = new ModelAndView();
		
		List<Genero> generos = generoService.todosGeneros();
		mav.addObject("generos", generos);
		
		if(auth != null) {
			Usuario usuario = (Usuario) auth.getPrincipal();
			mav.addObject("usuario", usuario);
			mav.addObject("nuevoGenero", new Genero());
		}
		
		mav.setViewName("genero/generos");
		return mav;
	}

	@PostMapping("/addGenero")
	public ModelAndView addGenero(@ModelAttribute Genero genero, Authentication auth) {
		ModelAndView mav = new ModelAndView();
		
		genero.setCodigo(genero.getCodigo().toUpperCase());
		generoService.add(genero);
		
		mav.setViewName("redirect:/genero/generos");
		return mav;
	}
	
	@GetMapping("/borrarGenero/{genero}")
	public ModelAndView deleteGenero(@ModelAttribute Genero genero) {
		ModelAndView mav = new ModelAndView();
		
		generoService.borrarPorCodigo(genero);
		
		mav.setViewName("redirect:/genero/generos");
		return mav;
	}
}
