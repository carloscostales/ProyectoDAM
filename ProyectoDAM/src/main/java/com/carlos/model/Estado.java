package com.carlos.model;

import java.util.HashSet;
import java.util.Set;

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

@Entity
@Table(name="estados")
public class Estado {
	
	public Estado() {
	}
	
	public Estado(Integer id) {
		this.id = id;
	}
	
	public Estado(Integer id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	@NotBlank(message="El nombre no puede estar vacío.")
	private String nombre;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy = "estado", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<LibroEstadoUsuario> libro_estado_usuario = new HashSet<>();
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Estado [id=" + id + ", nombre=" + nombre + "]";
	}
}
