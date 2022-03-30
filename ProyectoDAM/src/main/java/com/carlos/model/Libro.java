package com.carlos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="libro")
public class Libro {
	
	public Libro() {
	}

	public Libro(String isbn, String titulo, String editorial, Integer ano, Integer paginas, Autor autor) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.editorial = editorial;
		this.ano = ano;
		this.paginas = paginas;
		this.autor = autor;
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
	
	@ManyToOne
	private Autor autor = new Autor();
	
	@ManyToOne
	private Genero genero = new Genero();
	

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

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	@Override
	public String toString() {
		return "Libro [isbn=" + isbn + ", titulo=" + titulo + ", editorial=" + editorial + ", paginas=" + paginas
				+ ", ano=" + ano + ", autor=" + autor + ", genero=" + genero + "]";
	}
}
