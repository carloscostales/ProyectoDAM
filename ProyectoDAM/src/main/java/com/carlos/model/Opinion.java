package com.carlos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Opinion {
    
    public Opinion() {
	}
	
	public Opinion(Integer id, double valoracion, Libro libro, Usuario usuario) {
		this.id = id;
        this.valoracion = valoracion;
		this.libro = libro;
		this.usuario = usuario;
	}

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column
    private double valoracion;

    @Column
    private String comentario;

    @ManyToOne
    @JoinColumn
    private Libro libro;

    @ManyToOne
    @JoinColumn
    private Usuario usuario;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getValoracion() {
        return valoracion;
    }

    public void setValoracion(double valoracion) {
        this.valoracion = valoracion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Opinion [id=" + id + ", libro=" + libro + ", usuario=" + usuario + "]";
    }
}
