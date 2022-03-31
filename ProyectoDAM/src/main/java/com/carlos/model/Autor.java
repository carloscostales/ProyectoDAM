package com.carlos.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="autor")
public class Autor {
	
	public Autor() {
	}
	
	public Autor(int id, String nombre, String pais, String fechaNacimiento, String bio) {
		this.id = id;
		this.nombre = nombre;
		this.pais = pais;
		this.fechaNacimiento = fechaNacimiento;
		this.bio = bio;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column
	@NotBlank(message = "El nombre del autor no puede estar vacío.")
	private String nombre;

	@Column
	@NotBlank(message = "El pais no puede estar vacío.")
	private String pais;
	
	@Column
	@NotBlank(message = "El año de nacimiento no puede estar vacío.")
	private String fechaNacimiento;
	
	@Column
	@Length(max = 1000)
	private String bio;
	

	@OneToMany(fetch=FetchType.EAGER, mappedBy="autor", cascade=CascadeType.ALL)
	private List<Libro> libros = new ArrayList<Libro>();
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

	@Override
	public String toString() {
		return "Autor [id=" + id + ", nombre=" + nombre + ", pais=" + pais + ", fechaNacimiento=" + fechaNacimiento + "]";
	}
}
