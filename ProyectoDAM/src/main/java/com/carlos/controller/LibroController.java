package com.carlos.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.carlos.model.Autor;
import com.carlos.model.Libro;
import com.carlos.model.LibroEstadoUsuario;
import com.carlos.model.Opinion;
import com.carlos.model.Usuario;
import com.carlos.service.IServiceEstado;
import com.carlos.service.IServiceGenero;
import com.carlos.service.IServiceLibro;
import com.carlos.service.IServiceLibroEstadoUsuario;
import com.carlos.service.IServiceOpinion;
import com.carlos.service.IServiceSeguir;

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
	
	@Autowired
	private IServiceSeguir seguirService;

	@Autowired
	private IServiceOpinion opinionService;


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
		List<Libro> listaLibrosShuffle = libroService.listarLibrosPorGenero(libro.getGenero().getCodigo());
		Collections.shuffle(listaLibrosShuffle);
		mav.addObject("librosGenero", listaLibrosShuffle);
		mav.addObject("librosAutor", libroService.listarLibrosAutor(libro.getAutor().getId()));
		mav.addObject("numeroSeguidores", seguirService.numeroSeguidores(libro.getAutor().getId()));
		mav.addObject("libroDestacado", libroService.libroDestacadoPorAutor(libro.getAutor().getId()));

		mav.addObject("opinion", new Opinion());
		mav.addObject("opiniones", opinionService.listarOpinionesPorLibro(libro.getIsbn()));
		
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
	
	@PostMapping("/addLibro/{autor}")
	public ModelAndView addLibro(@Valid @ModelAttribute Libro libro, BindingResult bindingResult, Autor autor, @RequestParam("portada") MultipartFile multipartFile, Authentication auth) throws IOException {
		ModelAndView mav = new ModelAndView();
		
		if(bindingResult.hasErrors()) {
			if (bindingResult.hasFieldErrors("portada") && bindingResult.getErrorCount() == 1) {
				System.out.println("tiene");
				
			} else {
				mav.setViewName("libro/nuevoLibro");
				mav.addObject("generos", generoService.todosGeneros());
				
				if(auth != null) {
					Usuario usuario = (Usuario) auth.getPrincipal();
					mav.addObject("usuario", usuario);
				}
				return mav;
			}
		}
		
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		libro.setPortada(fileName);
		libroService.add(libro);
		
		String uploadDir = "./src/main/resources/static/img/autor-fotos/" + libro.getAutor().getId() +"/libros-portadas";
		Path uploadPath = Paths.get(uploadDir);
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		
		try (InputStream inputStream = multipartFile.getInputStream()){
			Path filePath = uploadPath.resolve(fileName);
			System.out.println("FILEPATH - " + filePath.toFile().getAbsolutePath());
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);	
		} catch (IOException e) {
			
		}
		
		mav.setViewName("redirect:/autor/ver/" + libro.getAutor().getId());
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
	
	@PostMapping("/changePortada")
	public ModelAndView changePortada(@Valid @ModelAttribute Libro libro, BindingResult bindingResult, @RequestParam("portada") MultipartFile multipartFile) throws IOException {
		ModelAndView mav = new ModelAndView();
		
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		libro.setPortada(fileName);
		libroService.add(libro);
		
		String uploadDir = "./src/main/resources/static/img/autor-fotos/" + libro.getAutor().getId() +"/libros-portadas";
		Path uploadPath = Paths.get(uploadDir);
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		
		try (InputStream inputStream = multipartFile.getInputStream()){
			Path filePath = uploadPath.resolve(fileName);
			System.out.println("FILEPATH - " + filePath.toFile().getAbsolutePath());
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);	
		} catch (IOException e) {
			
		}
		
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
