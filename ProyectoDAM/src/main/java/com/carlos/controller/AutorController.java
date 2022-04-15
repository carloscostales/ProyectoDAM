package com.carlos.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
import com.carlos.model.Seguir;
import com.carlos.model.Usuario;
import com.carlos.service.IServiceAutor;
import com.carlos.service.IServiceLibro;
import com.carlos.service.IServiceSeguir;

@Controller
@RequestMapping("/autor")
public class AutorController {
	
	@Autowired
	private IServiceAutor autorService;
	
	@Autowired 
	private IServiceSeguir seguirService;

	@Autowired 
	private IServiceLibro libroService;
	
	
	@GetMapping("/autores")
	public ModelAndView autores(Authentication auth) {
		ModelAndView mav = new ModelAndView();
		
		List<Autor> autores = autorService.listarAutores();
		mav.addObject("autores", autores);
		
		if(auth != null) {
			Usuario usuario = (Usuario) auth.getPrincipal();
			mav.addObject("usuario", usuario);
		}

		mav.setViewName("autor/autores");
		return mav;
	}
	
	@GetMapping("/ver/{autor}")
	public ModelAndView verAutor(@PathVariable Autor autor, Authentication auth) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("autor", autor);
		mav.addObject("seguir", new Seguir());
		mav.addObject("libros", libroService.listarLibrosAutor(autor.getId()));
		mav.addObject("numeroSeguidores", seguirService.numeroSeguidores(autor.getId()));
		
		if(auth != null) {
			Usuario usuario = (Usuario) auth.getPrincipal();
			mav.addObject("usuario", usuario);

			int seguidoInt = seguirService.comprobarSeguir(autor.getId(), usuario.getNombreUsuario());
			boolean seguido = false;
			if(seguidoInt == 1) {
				seguido = true;
				mav.addObject("seguir", seguirService.buscarSeguir(autor.getId(), usuario.getNombreUsuario()));
			}
			mav.addObject("seguido", seguido);
		}

		mav.setViewName("autor/verAutor");
		return mav;
	}
	
	@GetMapping("/nuevoAutor")
	public ModelAndView nuevoAutor(Authentication auth) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("autor", new Autor());
		
		if(auth != null) {
			Usuario usuario = (Usuario) auth.getPrincipal();
			mav.addObject("usuario", usuario);
		}

		mav.setViewName("autor/nuevoAutor");
		return mav;
	}
	
	@PostMapping("/addAutor")
	public ModelAndView addGenero(@Valid @ModelAttribute Autor autor, BindingResult bindingResult, @RequestParam("foto") MultipartFile multipartFile, Authentication auth) throws IOException {
		ModelAndView mav = new ModelAndView();
		
		if(bindingResult.hasErrors()) {
			if (bindingResult.hasFieldErrors("foto") && bindingResult.getErrorCount() == 1) {
				System.out.println("tiene");
				
			} else {
				mav.setViewName("autor/nuevoAutor");
				
				if(auth != null) {
					Usuario usuario = (Usuario) auth.getPrincipal();
					mav.addObject("usuario", usuario);
				}
				return mav;
			}
		}
		
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		autor.setFoto(fileName);
		autorService.add(autor);

		String uploadDir = "./src/main/resources/static/img/autor-fotos/" + autor.getId();
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
		
		mav.setViewName("redirect:/autor/autores");
		return mav;
	}
	
	@GetMapping("/editar/{autor}")
	public ModelAndView editarAutor(@PathVariable Autor autor, Authentication auth) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("autor", autor);
		
		if(auth != null ) {
			Usuario usuario = (Usuario) auth.getPrincipal();
			mav.addObject("usuario", usuario);
		}
		
		mav.setViewName("autor/editarAutor");
		return mav;
	}
	
	@PostMapping("/updateAutor")
	public ModelAndView updateAutor(@Valid @ModelAttribute Autor autor, BindingResult bindingResult, Authentication auth) {
		ModelAndView mav = new ModelAndView();

		if(bindingResult.hasErrors()) {
			mav.setViewName("autor/nuevoAutor");
			
			if(auth != null ) {
				Usuario usuario = (Usuario) auth.getPrincipal();
				mav.addObject("usuario", usuario);
			}
			return mav;
		}
		
		autorService.update(autor);

		mav.setViewName("redirect:/autor/ver/" + autor.getId());
		return mav;
	}
	
	@GetMapping("/borrarAutor/{autor}")
	public ModelAndView borrarAutor(@PathVariable Autor autor) {
		
		 ModelAndView mav = new ModelAndView();
		 if (libroService.listarLibrosAutor(autor.getId()).size() == 0) {
			 autorService.delete(autor);
			 mav.setViewName("redirect:/autor/autores");
		 } else
			 mav.setViewName("redirect:/autor/ver/" + autor.getId());
		 
		 return mav;
	 }
}
