package com.carlos.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.carlos.service.IServiceUsuario;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private IServiceUsuario usuarioService;
	

	@GetMapping("/usuarios")
	public ModelAndView usuarios(Authentication auth) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("usuario/usuarios");
		List<Usuario> lista = (List<Usuario>) usuarioService.todosUsuarios();
		
		mav.addObject("usuarios", lista);
		
		if(auth != null) {
			Usuario usuario = (Usuario) auth.getPrincipal();
			mav.addObject("usuario", usuario);
		}
		
		return mav;
	}
	
	@GetMapping("/ver/{usuario}")
	public ModelAndView usuario(@PathVariable Usuario usuario) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("usuario/verUsuario");
		mav.addObject("usuario", usuario);
		
		return mav;
	}

	@GetMapping("/registro")
	public ModelAndView registro() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("usuario/registro");
		
		Usuario usuario = new Usuario();
		mav.addObject("usuario", usuario);
		
		return mav;
	}
	
	@PostMapping("/addUsuario")
	public ModelAndView addUser(@Valid @ModelAttribute Usuario usuario, BindingResult bindingResult) {
		
		ModelAndView mav = new ModelAndView();
		
		if(bindingResult.hasErrors()) {
			mav.setViewName("usuario/registro");
			
			return mav;
		}
		
		Rol rol = new Rol();
		usuario.setRol(rol);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		usuario.setRegistrado(sdf.format(new Date()));

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		usuario.setContrasena(passwordEncoder.encode(usuario.getPassword()));
		
		usuarioService.add(usuario);
		
		mav.setViewName("redirect:/");
		
		return mav;
	}
}
