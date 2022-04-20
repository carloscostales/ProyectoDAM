package com.carlos.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="usuario")
public class Usuario implements UserDetails{
	
	public Usuario() { }
	
	public Usuario(String nombreUsuario, String contrasena, String nombre, String email) {
		this.nombreUsuario = nombreUsuario;
		this.contrasena = contrasena;
		this.nombre = nombre;
		this.email = email;
	}

	@Id
	@NotBlank(message = "El nombre del usuario no puede estar vacío.")
	private String nombreUsuario;

	@Column
	@NotBlank(message = "La contraseña no puede estar vacía.")
	private String contrasena;

	@Column
	@Pattern(regexp="^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message="Email incorrecto. Ej.: ejemplo@gmail.com")  
	private String email;

	@Column
	@NotBlank(message = "El nombre no puede estar vacío.")
	private String nombre;

	@Column
	@NotBlank(message = "El campo apellidos no puede estar vacío.")
	private String apellidos;

	@Column
	private String ultimoLogin;
	
	@Column
	private String registrado;

	@ManyToOne
	private Rol rol = new Rol();
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Seguir> seguir = new HashSet<>();
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Seguir> libro_estado_usuario = new HashSet<>();
	
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreusuario) {
		this.nombreUsuario = nombreusuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public String getUltimoLogin() {
		return ultimoLogin;
	}

	public void setUltimoLogin(String ultimoLogin) {
		this.ultimoLogin = ultimoLogin;
	}

	public String getRegistrado() {
		return registrado;
	}

	public void setRegistrado(String registrado) {
		this.registrado = registrado;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}	

	public Set<Seguir> getSeguir() {
		return seguir;
	}

	public void setSeguir(Set<Seguir> seguir) {
		this.seguir = seguir;
	}

	public Set<Seguir> getLibro_estado_usuario() {
		return libro_estado_usuario;
	}

	public void setLibro_estado_usuario(Set<Seguir> libro_estado_usuario) {
		this.libro_estado_usuario = libro_estado_usuario;
	}

	public String getNombreCompleto() {
		return nombre + " " + apellidos;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
	    grantedAuthorities.add(new SimpleGrantedAuthority(rol.getNombre()));
	    	    
	    return grantedAuthorities;
	}

	@Override
	public String getPassword() {

		return this.getContrasena();
	}

	@Override
	public String getUsername() {

		return this.getNombreUsuario();
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

	@Override
	public String toString() {
		return "Usuario [nombreUsuario=" + nombreUsuario + ", contrasena=" + contrasena + ", nombre=" + nombre
				+  ", email=" + email + "]";
	}
}
