package com.carlos.model;

import java.beans.Transient;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="libro")
public class Libro {
	
	public Libro() {
	}

	public Libro(String isbn, String titulo, String editorial, Integer ano, Integer paginas, String sinopsis, String portada, Autor autor, Genero genero) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.editorial = editorial;
		this.ano = ano;
		this.paginas = paginas;
		this.sinopsis = sinopsis;
		this.portada = portada;
		this.autor = autor;
		this.genero = genero;
	}

	@Id
	@Pattern(regexp="^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$", message="ISBN incorrecto.")
	private String isbn;
	
	@Column
	@NotBlank(message="El título no puede estar vacío.")
	private String titulo;
	
	@Column
	@NotBlank(message="La editorial no puede estar vacía.")
	private String editorial;
	
	@Column
	@NotNull(message="El número de páginas no puede ser nulo.")
	@Min(0)
	private Integer paginas;
	
	@Column
	@NotNull(message="El año no puede ser nulo.")
	@Min(0)
	@Max(value=2022, message="No puede ser mayor que este año.")
	private Integer ano;
	
	@Column
	@NotBlank(message="La sinopsis no puede ser nula.")
	@Length(max=10000)
	private String sinopsis;
	
	@Column
	private String portada;
	
	@ManyToOne
	private Autor autor = new Autor();
	
	@ManyToOne
	private Genero genero = new Genero();
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy = "libro", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<LibroEstadoUsuario> libro_estado_usuario = new HashSet<>();
	

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public Integer getPaginas() {
		return paginas;
	}

	public void setPaginas(Integer paginas) {
		this.paginas = paginas;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}
	
	public Autor getAutor() {
		return autor;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public String getPortada() {
		return portada;
	}

	public void setPortada(String portada) {
		this.portada = portada;
	}


	@Transient
	public String getPortadaPath() {
		if (portada == null || isbn == null) return null;
			
		return "/img/autor-fotos/" + autor.getId() + "/libros-portadas/" + portada;
	}

	@Override
	public String toString() {
		return "Libro [isbn=" + isbn + ", titulo=" + titulo + ", editorial=" + editorial + ", paginas=" + paginas
				+ ", ano=" + ano + ", autor=" + autor + ", genero=" + genero + "]";
	}
}
