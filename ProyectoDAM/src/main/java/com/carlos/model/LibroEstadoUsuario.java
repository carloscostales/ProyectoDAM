package com.carlos.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="libro_estado_usuario")
public class LibroEstadoUsuario {
	
	public LibroEstadoUsuario() {
	}
	
	public LibroEstadoUsuario(int id, Libro libro, Estado estado, Usuario usuario) {
		this.id = id;
		this.libro = libro;
		this.estado = estado;
		this.usuario = usuario;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
    @JoinColumn
    private Libro libro;
	
    @ManyToOne
    @JoinColumn
    private Estado estado;
    
    @ManyToOne
    @JoinColumn
    private Usuario usuario;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
