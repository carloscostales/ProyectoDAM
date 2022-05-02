package com.carlos.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.carlos.model.Genero;
import com.carlos.model.Libro;
import com.carlos.model.Usuario;
import com.carlos.service.IServiceGenero;
import com.carlos.service.IServiceLibro;

@Controller
@RequestMapping("/genero")
public class GeneroController {

	@Autowired
	private IServiceGenero generoService;
	
	@Autowired
	private IServiceLibro libroService;
	
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

	@GetMapping("/ver/{genero}")
	public ModelAndView verGenero(@PathVariable Genero genero, @RequestParam Map<String, Object> params, Authentication auth) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("busqueda", new Genero());
		
		int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
		PageRequest pageRequest = PageRequest.of(page, 6);
		Page<Libro> pageGenero =  (Page<Libro>) libroService.listarLibrosPorGenero(genero.getCodigo(), pageRequest);
		int totalPage = pageGenero.getTotalPages();
		if (totalPage > 0) {
			List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			mav.addObject("paginas", pages);
		}

		mav.addObject("listaLibrosGenero", pageGenero.getContent());
		mav.addObject("current", page+1);
		mav.addObject("next", page+2);
		mav.addObject("prev", page);
		mav.addObject("last", totalPage);

		mav.addObject("librosGenero", libroService.listarLibrosPorGenero(genero.getCodigo()));
		mav.addObject("numeroLibros", libroService.listarLibrosPorGenero(genero.getCodigo()).size());
		
		if(auth != null) {
			Usuario usuario = (Usuario) auth.getPrincipal();
			mav.addObject("usuario", usuario);
		}

		mav.setViewName("genero/verGenero");
		return mav;
	}

	@GetMapping("/ver/{genero}/buscar")
	public ModelAndView verGeneroBuscar(@PathVariable Genero genero, @RequestParam Map<String, Object> params, @RequestParam String nombre, @ModelAttribute("busqueda") Genero busqueda, Authentication auth) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("busqueda", busqueda);

		int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
		PageRequest pageRequest = PageRequest.of(page, 6);
		Page<Libro> pageGenero =  (Page<Libro>) libroService.listarLibrosPorGeneroBusqueda(genero.getCodigo(), busqueda.getNombre(), pageRequest);
		int totalPage = pageGenero.getTotalPages();
		if (totalPage > 0) {
			List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			mav.addObject("paginas", pages);
		}

		mav.addObject("listaLibrosGenero", pageGenero.getContent());
		mav.addObject("current", page+1);
		mav.addObject("next", page+2);
		mav.addObject("prev", page);
		mav.addObject("last", totalPage);

		mav.addObject("numeroLibros", libroService.listarLibrosPorGeneroBusqueda(genero.getCodigo(), busqueda.getNombre(), pageRequest).toList().size());
		
		if(auth != null) {
			Usuario usuario = (Usuario) auth.getPrincipal();
			mav.addObject("usuario", usuario);
		}
		
		mav.addObject("busqueda", new Genero());
		mav.setViewName("genero/busquedaGenero");
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
	
	@PostMapping("/updateGenero")
	public ModelAndView updateGenero(@ModelAttribute Genero genero) {
		ModelAndView mav = new ModelAndView();
		
		genero.setCodigo(genero.getCodigo().toUpperCase());
		generoService.update(genero);
		
		mav.setViewName("redirect:/genero/ver/" + genero.getCodigo());
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
