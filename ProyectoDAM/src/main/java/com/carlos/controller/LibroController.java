package com.carlos.controller;

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

import com.carlos.model.Autor;
import com.carlos.model.Libro;
import com.carlos.model.Usuario;
import com.carlos.service.IGeneroService;
import com.carlos.service.ILibroService;

@Controller
@RequestMapping("/libro")
public class LibroController {
	
	@Autowired
	private ILibroService libroService;
	
	@Autowired
	private IGeneroService generoService;


	@GetMapping("/libros")
	public ModelAndView verLibros(Authentication auth) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("libro/libros");
		
		mav.addObject("libros", libroService.listarLibros());
		if(auth != null) {
			Usuario usuario = (Usuario) auth.getPrincipal();
			mav.addObject("usuario", usuario);
		}
		
		return mav;
	}
	
	@GetMapping("/nuevoLibro/{autor}")
	public ModelAndView nuevoLibro(@PathVariable Autor autor, Authentication auth) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("libro/nuevoLibro");
		
		mav.addObject("libro", new Libro());
		mav.addObject("autor", autor);
		mav.addObject("generos", generoService.todosGeneros());

		if(auth != null) {
			Usuario usuario = (Usuario) auth.getPrincipal();
			mav.addObject("usuario", usuario);
		}
		
		return mav;
	}
	
	@PostMapping("/addLibro")
	public ModelAndView addLibro(@Valid @ModelAttribute Libro libro, BindingResult bindingResult, Autor autor, Authentication auth) {
		ModelAndView mav = new ModelAndView();
		
		if(bindingResult.hasErrors()) {
			mav.setViewName("libro/nuevoLibro");
			
			if(auth != null) {
				Usuario usuario = (Usuario) auth.getPrincipal();
				mav.addObject("usuario", usuario);
			}
			
			mav.addObject("generos", generoService.todosGeneros());
			return mav;
		}
		
		libroService.add(libro);
		
		mav.setViewName("redirect:/");
		
		return mav;
	}
}
