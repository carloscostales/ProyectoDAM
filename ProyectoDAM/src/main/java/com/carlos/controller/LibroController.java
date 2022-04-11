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
import com.carlos.model.LibroEstadoUsuario;
import com.carlos.model.Usuario;
import com.carlos.service.IServiceEstado;
import com.carlos.service.IServiceGenero;
import com.carlos.service.IServiceLibro;
import com.carlos.service.IServiceLibroEstadoUsuario;

@Controller
@RequestMapping("/libro")
public class LibroController {
	
	@Autowired
	private IServiceLibro libroService;
	
	@Autowired
	private IServiceGenero generoService;
	
	@Autowired
	private IServiceLibroEstadoUsuario estadoLibUsService;
	
	@Autowired
	private IServiceEstado estadoService;


	@GetMapping("/libros")
	public ModelAndView verLibros(Authentication auth) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("libros", libroService.listarLibros());
		if(auth != null) {
			Usuario usuario = (Usuario) auth.getPrincipal();
			mav.addObject("usuario", usuario);
		}

		mav.setViewName("libro/libros");
		return mav;
	}
	
	@GetMapping("/ver/{libro}")
	public ModelAndView verLibros(@PathVariable Libro libro, Authentication auth) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("libro", libro);
		
		if(auth != null) {
			Usuario usuario = (Usuario) auth.getPrincipal();
			mav.addObject("usuario", usuario);

			mav.addObject("leuNuevo", new LibroEstadoUsuario());
			
			LibroEstadoUsuario leu = estadoLibUsService.estadoDeLibro(libro, usuario);
			mav.addObject("estados", estadoService.listarEstados());
			
			if(leu != null) {
				mav.addObject("leuActual", leu);
			}
		}

		mav.setViewName("libro/verLibro");
		return mav;
	}
	
	@GetMapping("/nuevoLibro/{autor}")
	public ModelAndView nuevoLibro(@PathVariable Autor autor, Authentication auth) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("libro", new Libro());
		mav.addObject("autor", autor);
		mav.addObject("generos", generoService.todosGeneros());

		if(auth != null) {
			Usuario usuario = (Usuario) auth.getPrincipal();
			mav.addObject("usuario", usuario);
		}

		mav.setViewName("libro/nuevoLibro");
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
		
		mav.setViewName("redirect:/autor/ver/" + autor.getId());
		return mav;
	}
	
	@GetMapping("/editar/{libro}")
	public ModelAndView editarLibro(@PathVariable Libro libro, Authentication auth) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("libro", libro);
		mav.addObject("generos", generoService.todosGeneros());

		if(auth != null) {
			Usuario usuario = (Usuario) auth.getPrincipal();
			mav.addObject("usuario", usuario);
		}

		mav.setViewName("libro/editarLibro");
		return mav;
	}
	
	@PostMapping("/updateLibro")
	public ModelAndView updateAutor(@Valid @ModelAttribute Libro libro, BindingResult bindingResult, Authentication auth) {
		ModelAndView mav = new ModelAndView();
		System.out.println(libro.toString());
		
		if(bindingResult.hasErrors()) {
			mav.setViewName("libro/editarLibro");
			mav.addObject("generos", generoService.todosGeneros());
			
			if(auth != null ) {
				Usuario usuario = (Usuario) auth.getPrincipal();
				mav.addObject("usuario", usuario);
			}
			return mav;
		}
		
		libroService.update(libro);

		mav.setViewName("redirect:/libro/ver/" + libro.getIsbn());
		return mav;
	}
	
	@GetMapping("/borrar/{libro}")
	public ModelAndView borrarLibro(@PathVariable Libro libro) {
		ModelAndView mav = new ModelAndView();
		
		estadoLibUsService.deleteByLibro(libro.getIsbn());
		libroService.delete(libro);
		
		mav.setViewName("redirect:/autor/ver/" + libro.getAutor().getId());
		return mav;
	}
}
