package com.carlos.model;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="autor")
@EntityListeners(AuditingEntityListener.class) // necesario para createdAt
public class Autor {
	
	public Autor() {
	}
	
	public Autor(Integer id, String nombre, String pais, String fechaNacimiento, String bio) {
		this.id = id;
		this.nombre = nombre;
		this.pais = pais;
		this.fechaNacimiento = fechaNacimiento;
		this.bio = bio;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
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
	@NotBlank(message = "La biografía no puede estar vacía.")
	@Length(max = 5000)
	private String bio;
	
	@Column(nullable=true)
	private String foto;

	@CreatedDate
	@Column(name="created_at", nullable = false, updatable = false)
	private Date createdAt;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="autor", cascade=CascadeType.ALL)
	private List<Libro> libros = new ArrayList<Libro>();
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Seguir> seguir = new HashSet<>();
	
	
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

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}
	
	public Set<Seguir> getSeguir() {
		return seguir;
	}

	public void setSeguir(Set<Seguir> seguir) {
		this.seguir = seguir;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	@Transient
	public String getFotoPath() {
		if (foto == null || id == null) return null;
			
		return "/img/autor-fotos/" + id + "/" + foto;
	}

	@Override
	public String toString() {
		return "Autor [id=" + id + ", nombre=" + nombre + ", pais=" + pais + ", fechaNacimiento=" + fechaNacimiento + "]";
	}
}
