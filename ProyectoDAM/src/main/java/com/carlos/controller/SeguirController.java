package com.carlos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.carlos.model.Seguir;
import com.carlos.model.Usuario;
import com.carlos.service.IServiceSeguir;

@Controller
@RequestMapping("/seguir")
public class SeguirController {

	@Autowired
	private IServiceSeguir seguirService;

	@PostMapping("/addSeguir")
	 private ModelAndView seguir(@ModelAttribute Seguir seguir, Authentication auth) {
		 ModelAndView mav = new ModelAndView();
		 mav.setViewName("redirect:/autor/ver/" + seguir.getAutor().getId());
		 
		 Usuario usuario = (Usuario) auth.getPrincipal();
		 if(auth != null) {
			
			mav.addObject("usuario", usuario);
		 }
		 
		 seguir.setUsuario(usuario);
		 
		 seguirService.add(seguir);
		 
		 return mav;
	 }
}
