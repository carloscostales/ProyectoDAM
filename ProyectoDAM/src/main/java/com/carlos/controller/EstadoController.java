package com.carlos.controller;

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

import com.carlos.model.Autor;
import com.carlos.model.Estado;
import com.carlos.model.Usuario;
import com.carlos.service.IServiceEstado;

@Controller
@RequestMapping("/estado")
public class EstadoController {

	@Autowired
	private IServiceEstado estadoService;
	
	@GetMapping("/estados")
	public ModelAndView estados(Authentication auth) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("estado/estados");
		mav.addObject("estados", estadoService.listarEstados());
		
		if(auth != null) {
			Usuario usuario = (Usuario) auth.getPrincipal();
			mav.addObject("usuario", usuario);
		}
		
		return mav;
	}
	
	@GetMapping("/nuevoEstado")
	public ModelAndView nuevoEstado(Authentication auth) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("estado/nuevoEstado");
		mav.addObject("estado", new Estado());
		
		if(auth != null) {
			Usuario usuario = (Usuario) auth.getPrincipal();
			mav.addObject("usuario", usuario);
		}
		
		return mav;
	}
	
	@PostMapping("/addEstado")
	public ModelAndView addEstado(@Valid @ModelAttribute Estado estado, BindingResult bindingResult, Authentication auth) {
		ModelAndView mav = new ModelAndView();
		
		if(bindingResult.hasErrors()) {
			mav.setViewName("estado/nuevoEstado");
			
			if(auth != null) {
				Usuario usuario = (Usuario) auth.getPrincipal();
				mav.addObject("usuario", usuario);
			}
			
			return mav;
		}
		
		estadoService.add(estado);
		
		mav.setViewName("redirect:/estado/estados");
		
		return mav;
	}
}
