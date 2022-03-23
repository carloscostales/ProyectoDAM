package com.carlos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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

	
	@Override
	public String toString() {
		return "Genero [codigo=" + codigo + ", nombre=" + nombre + "]";
	}
}
