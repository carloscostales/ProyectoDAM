package com.carlos.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Seguir implements Serializable  {
	
	public Seguir() {
	}

	public Seguir(Integer id, Autor autor, Usuario usuario) {
		this.id = id;
		this.autor = autor;
		this.usuario = usuario;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
    @JoinColumn
    private Autor autor;
	
    @ManyToOne
    @JoinColumn
    private Usuario usuario;

    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Seguir [id=" + id + ", autor=" + autor + ", usuario=" + usuario + "]";
	}
}
