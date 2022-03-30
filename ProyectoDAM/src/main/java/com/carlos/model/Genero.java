package com.carlos.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;

@Entity
public class Genero {

	public Genero() { }
	
	public Genero(String codigo, String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
	}

	@Id
	@Pattern(regexp="^[A-Z]{3}$", message="Código incorrecto. Debe tener tres letras mayusculas de la A-Z.")
	private String codigo;
	
	@Column
	private String nombre;

	@OneToMany(fetch=FetchType.EAGER, mappedBy="genero", cascade=CascadeType.ALL)
	private List<Libro> libros = new ArrayList<Libro>();
	

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

	@Override
	public String toString() {
		return "Genero [codigo=" + codigo + ", nombre=" + nombre + "]";
	}
}
