package com.carlos.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Entity
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

	@ManyToOne
	private Rol rol = new Rol();
	
	
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

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
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