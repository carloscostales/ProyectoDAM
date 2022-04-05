package com.carlos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.carlos.model.Estado;
import com.carlos.model.LibroEstadoUsuario;
import com.carlos.model.Usuario;
import com.carlos.service.IServiceLibroEstadoUsuario;

@Controller
public class LibroEstadoUsuarioController {
	
	@Autowired
	private IServiceLibroEstadoUsuario leuService;

	@PostMapping("/addLeu")
	 private ModelAndView addLeu(@ModelAttribute LibroEstadoUsuario leu, Authentication auth) {
		 ModelAndView mav = new ModelAndView();
		 mav.setViewName("redirect:/libro/ver/" + leu.getLibro().getIsbn());
		 
		 Usuario usuario = (Usuario) auth.getPrincipal();
		 if(auth != null) {
			
			mav.addObject("usuario", usuario);
		 }
		 Estado est = new Estado(1);
		 leu.setEstado(est);
		 leuService.add(leu);
		 
		 return mav;
	 }
	
	@PostMapping("/updateLeu")
	 private ModelAndView updateLeu(@ModelAttribute LibroEstadoUsuario leu) {
		 ModelAndView mav = new ModelAndView();

		 leuService.update(leu);
		 
		 mav.setViewName("redirect:/libro/ver/" + leu.getLibro().getIsbn());
		 
		 return mav;
	 }
	
	@GetMapping("/deleteLeu/{leu}")
	public String rutaEliminar(@PathVariable LibroEstadoUsuario leu) {
		
		leuService.deleteById(leu.getId());
		
		return "redirect:/libro/ver/" + leu.getLibro().getIsbn();
	}
}
