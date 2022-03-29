package com.carlos.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.carlos.model.Genero;
import com.carlos.model.Usuario;
import com.carlos.service.GeneroService;

@Controller
@RequestMapping("/genero")
public class GeneroController {

	@Autowired
	private GeneroService generoService;
	
	
	@GetMapping("/generos")
	public ModelAndView generos(Authentication auth) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("genero/generos");
		
		List<Genero> generos = generoService.todosGeneros();
		mav.addObject("generos", generos);
		
		if(auth != null) {
			Usuario usuario = (Usuario) auth.getPrincipal();
			mav.addObject("usuario", usuario);
		}
		
		return mav;
	}
	
	@GetMapping("/nuevoGenero")
	public ModelAndView nuevoGenero(Authentication auth) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("genero/nuevoGenero");
		
		Genero genero = new Genero();
		mav.addObject("genero", genero);
		
		if(auth != null) {
			Usuario usuario = (Usuario) auth.getPrincipal();
			mav.addObject("usuario", usuario);
		}
		
		return mav;
	}
	
	@PostMapping("/addGenero")
	public ModelAndView addGenero(@Valid @ModelAttribute Genero genero, BindingResult bindingResult, Authentication auth) {
		ModelAndView mav = new ModelAndView();
		
		if(bindingResult.hasErrors()) {
			mav.setViewName("genero/nuevoGenero");
			
			if(auth != null) {
				Usuario usuario = (Usuario) auth.getPrincipal();
				mav.addObject("usuario", usuario);
			}
			
			return mav;
		}
		
		generoService.add(genero);
		
		mav.setViewName("redirect:/genero/generos");
		
		return mav;
	}
}
